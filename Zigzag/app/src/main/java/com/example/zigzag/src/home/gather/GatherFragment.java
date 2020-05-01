package com.example.zigzag.src.home.gather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zigzag.R;
import com.example.zigzag.src.home.zigzag.MyCustomPagerAdapter;
import com.example.zigzag.src.outer.OuterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class GatherFragment extends Fragment {
    ViewGroup viewGroup;
    ViewPager viewPager;
    TabLayout tabLayout;
    private LinearLayout mBtnOuter, mBtnTop, mBtnDress, mBtnBottom, mBtnSkirt;

    private ScrollView mScrollView;
    private FloatingActionButton mFloatingBtn;

    int images[] = {R.drawable.gather_ad_1, R.drawable.gather_ad_2,R.drawable.gather_ad_3,R.drawable.gather_ad_4,R.drawable.gather_ad_5};
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
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_gather,container,false);

        viewPager = (ViewPager)viewGroup.findViewById(R.id.vp_gather_ad);
        myCustomPagerAdapter = new MyCustomPagerAdapter(getContext(), images);
        viewPager.setAdapter(myCustomPagerAdapter);

        tabLayout = viewGroup.findViewById(R.id.gather_ad_tab);
        tabLayout.setupWithViewPager(viewPager, true);

        mScrollView = viewGroup.findViewById(R.id.gather_scroll);
        mFloatingBtn = viewGroup.findViewById(R.id.gather_fab);

        mFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        mBtnOuter = viewGroup.findViewById(R.id.linear_gather_outer);
        mBtnTop = viewGroup.findViewById(R.id.linear_gather_top);
        mBtnDress = viewGroup.findViewById(R.id.linear_gather_dress);
        mBtnBottom = viewGroup.findViewById(R.id.linear_gather_bottom);
        mBtnSkirt = viewGroup.findViewById(R.id.linear_gather_skirt);

        mBtnOuter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OuterActivity.class);
                startActivity(intent);
            }
        });
//        mBtnTop.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), TopActivity.class);
//                startActivity(intent);
//            }
//        });
//        mBtnDress.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DressActivity.class);
//                startActivity(intent);
//            }
//        });
//        mBtnBottom.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), BottomActivity.class);
//                startActivity(intent);
//            }
//        });
//        mBtnSkirt.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SkirtActivity.class);
//                startActivity(intent);
//            }
//        });
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
