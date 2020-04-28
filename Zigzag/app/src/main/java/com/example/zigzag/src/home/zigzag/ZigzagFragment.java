package com.example.zigzag.src.home.zigzag;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zigzag.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class ZigzagFragment extends Fragment {
    ViewGroup viewGroup;
    ViewPager viewPager;
    TabLayout tabLayout;
    int images[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
    MyCustomPagerAdapter myCustomPagerAdapter;
    int currentPage = 0;

    Timer timer;
    //이미지 총 갯수
    final int NUM_PAGERS = images.length;
    //초기 웨이팅 타임
    final long DELAY_MS = 3000;
    //5초 주기로 작동
    final long PERIOD_MS = 4000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_zigzag,container,false);

        viewPager = (ViewPager)viewGroup.findViewById(R.id.vp_zig_banner);
        myCustomPagerAdapter = new MyCustomPagerAdapter(getContext(), images);
        viewPager.setAdapter(myCustomPagerAdapter);

        tabLayout = viewGroup.findViewById(R.id.zig_banner_tab);
        tabLayout.setupWithViewPager(viewPager, true);

        return viewGroup;
    }


    @Override
    public void onResume() {
        super.onResume();

        //Adapter 세팅 후 타이머 실행
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                currentPage = viewPager.getCurrentItem();
                int nextPage = currentPage + 1;

                if(nextPage >= NUM_PAGERS){
                    nextPage = 0;
                }
                viewPager.setCurrentItem(nextPage,true);
                currentPage = nextPage;
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },DELAY_MS,PERIOD_MS);
    }

    @Override
    public void onPause() {
        super.onPause();
        //다른 액티비티나 프래그먼트 실행시 타이머 제거
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

}
