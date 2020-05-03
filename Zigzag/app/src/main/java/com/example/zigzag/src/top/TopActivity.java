package com.example.zigzag.src.top;

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
import com.example.zigzag.src.top.blouse.BlouseFragment;
import com.example.zigzag.src.top.interfaces.TopActivityView;
import com.example.zigzag.src.top.shirt.ShirtFragment;
import com.example.zigzag.src.top.topall.TopAllFragment;
import com.example.zigzag.src.top.tshirt.TShirtFragment;
import com.google.android.material.tabs.TabLayout;

public class TopActivity extends BaseActivity implements TopActivityView {

    private Toolbar mToolbar;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        //toolbar 설정
        mToolbar = findViewById(R.id.top_toolbar);
        setSupportActionBar(mToolbar);
        //title 노출
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //뒤로가기 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        //getSupportActionBar().setElevation(0f);
        //tabLayout 생성
        TabLayout tabLayout = findViewById(R.id.top_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("전체"));
        tabLayout.addTab(tabLayout.newTab().setText("티셔츠"));
        tabLayout.addTab(tabLayout.newTab().setText("블라우스"));
        tabLayout.addTab(tabLayout.newTab().setText("셔츠/남방"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = findViewById(R.id.vp_top);

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
                    TopAllFragment all = new TopAllFragment();
                    return all;
                case 1:
                    TShirtFragment tshirt = new TShirtFragment();
                    return tshirt;
                case 2:
                    BlouseFragment blouse = new BlouseFragment();
                    return blouse;
                case 3:
                    ShirtFragment shirt = new ShirtFragment();
                    return shirt;
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

        final TopService topService = new TopService(this);
        topService.getTest();
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
