package com.example.zigzag.src.home.zigzag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.zigzag.R;
import com.example.zigzag.src.MyRecyclerItemsViewAdapter;
import com.example.zigzag.src.home.zigzag.interfaces.ZigzagFragmentView;
import com.example.zigzag.src.itemdetail.ItemDetailActivity;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class ZigzagFragment extends Fragment implements MyRecyclerItemsViewAdapter.OnItemClickListener, ZigzagFragmentView {
    ViewGroup viewGroup;
    ViewPager viewPager;
    TabLayout tabLayout;

    private NestedScrollView mScrollView;
    private FloatingActionButton mFloatingBtn;

    private RecyclerView mItemsRecyclerView;
    private MyRecyclerItemsViewAdapter mAdapter;
    private ArrayList<ItemsResponse.ItemsResult> mItemList = new ArrayList<ItemsResponse.ItemsResult>();
    private ZigzagService mZigzagService;

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

        mScrollView = viewGroup.findViewById(R.id.zig_scroll);
        mFloatingBtn = viewGroup.findViewById(R.id.zig_fab);

        mFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        mItemList = new ArrayList<ItemsResponse.ItemsResult>();
        initView(viewGroup);

        getItemList();

        return viewGroup;
    }

    void initView(View view) {
        mItemsRecyclerView = viewGroup.findViewById(R.id.rv_today_item);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        mItemsRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerItemsViewAdapter(mItemList, getContext());
        mAdapter.setOnItemClickListener(this);
        mItemsRecyclerView.setAdapter(mAdapter);
    }

    private void getItemList() {
        mZigzagService.getItemList();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mZigzagService = new ZigzagService(this);

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

    @Override
    public void validateSuccess(String text) {

    }
    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getItemSuccess(boolean isSuccess, int code, String message, ArrayList<ItemsResponse.ItemsResult> itemsResults) {
        if (isSuccess) {
            System.out.println("성공");
            mItemList.clear();
            mItemList.addAll(itemsResults);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(View view, ItemsResponse.ItemsResult item) {
        System.out.println(item.getItem_name());
        Intent intent = new Intent(getContext(), ItemDetailActivity.class);
        intent.putExtra("item_id",item.getItem_id());
        startActivity(intent);
    }
}
