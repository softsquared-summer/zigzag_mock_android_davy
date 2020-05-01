package com.example.zigzag.src.outer.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.zigzag.R;
import com.example.zigzag.src.home.shoppingmall.shoplike.ShopLikeFragment;
import com.example.zigzag.src.home.shoppingmall.shoprank.ShopRankFragment;
import com.google.android.material.tabs.TabLayout;

public class AllFragment extends Fragment {
    ViewGroup viewGroup;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_shop,container,false);

        //상단 TabLayout에 항목 추가
        TabLayout tabLayout = (TabLayout)viewGroup.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("랭킹"));
        tabLayout.addTab(tabLayout.newTab().setText("즐겨찾기"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)viewGroup.findViewById(R.id.vp_shop);
        //프래그먼트 안에 프래그먼트를 만들 때는 FragmentManager 대신 ChildFragmentManager 사용
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return viewGroup;
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
                    ShopRankFragment tab1 = new ShopRankFragment();
                    return tab1;
                case 1:
                    ShopLikeFragment tab2 = new ShopLikeFragment();
                    return tab2;
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
