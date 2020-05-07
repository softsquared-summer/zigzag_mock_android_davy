package com.example.zigzag.src.outer.all;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;
import com.example.zigzag.src.MyRecyclerViewAdapter;
import com.example.zigzag.src.itemdetail.ItemDetailActivity;
import com.example.zigzag.src.outer.all.interfaces.AllFragmentView;
import com.example.zigzag.src.outer.all.models.OuterAllResponse;

import java.util.ArrayList;
import java.util.Collection;

public class AllFragment extends Fragment implements MyRecyclerViewAdapter.OnItemClickListener, AllFragmentView {
    ViewGroup viewGroup;
    private RecyclerView mOuterAllRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private ArrayList<OuterAllResponse.OuterAllResult> mItemList = new ArrayList<OuterAllResponse.OuterAllResult>();
    private AllService allService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allService = new AllService(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_outer_all,container,false);
        mItemList = new ArrayList<OuterAllResponse.OuterAllResult>();
        initView(viewGroup);

        getItemList();

        return viewGroup;
    }

    void initView(View view){
        mOuterAllRecyclerView = viewGroup.findViewById(R.id.rv_outer_all);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),3);
        mOuterAllRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(mItemList, getContext());
        mAdapter.setOnItemClickListener(this);
        mOuterAllRecyclerView.setAdapter(mAdapter);
    }
    private void getItemList(){
        allService.getItemList();
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getItemSuccess(boolean isSuccess, int code, String message, ArrayList<OuterAllResponse.OuterAllResult> outerAllResult) {
        if(isSuccess){
            System.out.println("성공");
            mItemList.clear();
            mItemList.addAll((Collection<? extends OuterAllResponse.OuterAllResult>) outerAllResult);
            mAdapter.notifyDataSetChanged();

        }
    }


    @Override
    public void onItemClick(View view, OuterAllResponse.OuterAllResult item) {
        //아이템 클릭시 이벤트
        System.out.println(item.getItem_name());
        Intent intent = new Intent(getContext(), ItemDetailActivity.class);
        intent.putExtra("item_id",item.getItem_id());
        startActivity(intent);
    }
}
