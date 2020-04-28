package com.example.zigzag.src.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.zigzag.R;
import com.example.zigzag.src.BackPressCloseHandler;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.home.gather.GatherFragment;
import com.example.zigzag.src.home.interfaces.HomeActivityView;
import com.example.zigzag.src.home.likes.LikesFragment;
import com.example.zigzag.src.home.mypage.MypageFragment;
import com.example.zigzag.src.home.shoppingmall.ShoppingmallFragment;
import com.example.zigzag.src.home.zigzag.ZigzagFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends BaseActivity implements HomeActivityView {

    BottomNavigationView bottomNavigationView;
    ZigzagFragment zigzagFragment;
    ShoppingmallFragment shopFragment;
    GatherFragment gatherFragment;
    LikesFragment likesFragment;
    MypageFragment mypageFragment;

    int MAX_PAGE = 5;
    Fragment cur_fragment = new Fragment();

    private BackPressCloseHandler backPressCloseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        backPressCloseHandler = new BackPressCloseHandler(this);

        zigzagFragment = new ZigzagFragment();
        shopFragment = new ShoppingmallFragment();
        gatherFragment = new GatherFragment();
        likesFragment = new LikesFragment();
        mypageFragment = new MypageFragment();

        //제일 먼저 띄워 줄 뷰 세팅(bottom navigation만 사용시)
        //getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, zigzagFragment).commitAllowingStateLoss();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_home);
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));

        //제일 먼저 띄워 줄 뷰 세팅(view pager 같이 사용시)
        viewPager.setCurrentItem(0);

        //bottom Navigation 탭 선택시 선택한 프래그먼트로 이동
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1: {
                        //getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, zigzagFragment).commitAllowingStateLoss();
                        viewPager.setCurrentItem(0);
                        return true;
                    }
                    case R.id.tab2: {
                        //getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, shopFragment).commitAllowingStateLoss();
                        viewPager.setCurrentItem(1);
                        return true;
                    }
                    case R.id.tab3: {
                        //getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, gatherFragment).commitAllowingStateLoss();
                        viewPager.setCurrentItem(2);
                        return true;
                    }
                    case R.id.tab4: {
                        //getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, likesFragment).commitAllowingStateLoss();
                        viewPager.setCurrentItem(3);
                        return true;
                    }
                    case R.id.tab5: {
                        //getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, mypageFragment).commitAllowingStateLoss();
                        viewPager.setCurrentItem(4);
                        return true;
                    }
                    default:
                        return false;

                }
            }
        });

        //프래그먼트 변경시 bottom navigation 탭 선택변경
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.tab1).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.tab2).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.tab3).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.tab4).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.tab5).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private class adapter extends FragmentPagerAdapter{
        public adapter(FragmentManager fm){
            super(fm);
        }

        //스크롤시 프래그먼트 변경
        public Fragment getItem(int position){
            if(position<0 || MAX_PAGE<=position)
                return null;
            switch (position) {
                case 0:
                    cur_fragment = new ZigzagFragment();
                    break;
                case 1:
                    cur_fragment = new ShoppingmallFragment();
                    break;
                case 2:
                    cur_fragment = new GatherFragment();
                    break;
                case 3:
                    cur_fragment = new LikesFragment();
                    break;
                case 4:
                    cur_fragment = new MypageFragment();
                    break;
            }
            return  cur_fragment;
            }
            public int getCount(){
            return MAX_PAGE;
        }
    }

    private void tryGetTest() {
        showProgressDialog();

        final HomeService mainService = new HomeService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();

    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    //뒤로가기 누를 때 실행
    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
