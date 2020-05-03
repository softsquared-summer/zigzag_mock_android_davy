package com.example.zigzag.src.bottom;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.bottom.interfaces.BottomActivityView;
import com.example.zigzag.src.dress.dressall.BottomAllFragment;
import com.example.zigzag.src.dress.dresslong.ShortsFragment;
import com.example.zigzag.src.dress.middy.SlacksFragment;
import com.example.zigzag.src.dress.mini.StraightFragment;
import com.google.android.material.tabs.TabLayout;

public class BottomActivity extends BaseActivity implements BottomActivityView {

    private Toolbar mToolbar;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        //toolbar 설정
        mToolbar = findViewById(R.id.bottom_toolbar);
        setSupportActionBar(mToolbar);
        //title 노출
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //뒤로가기 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        //getSupportActionBar().setElevation(0f);
        //tabLayout 생성
        TabLayout tabLayout = findViewById(R.id.bottom_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("전체"));
        tabLayout.addTab(tabLayout.newTab().setText("일자바지"));
        tabLayout.addTab(tabLayout.newTab().setText("슬랙스팬츠"));
        tabLayout.addTab(tabLayout.newTab().setText("반바지"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = findViewById(R.id.vp_bottom);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public ViewPagerAdapter(FragmentManager fm, int NumOfTabs){
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    BottomAllFragment all = new BottomAllFragment();
                    return all;
                case 1:
                    StraightFragment straight = new StraightFragment();
                    return straight;
                case 2:
                    SlacksFragment slacks = new SlacksFragment();
                    return slacks;
                case 3:
                    ShortsFragment shorts = new ShortsFragment();
                    return shorts;
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void tryGetTest() {
        showProgressDialog();

        final BottomService bottomService = new BottomService(this);
        bottomService.getTest();
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

    public void customOnClick(View view) {
        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
            default:
                break;
        }
    }
}
