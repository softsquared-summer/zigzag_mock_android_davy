package com.example.zigzag.src.dress.dressall;

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
import com.example.zigzag.src.MyRecyclerItemsViewAdapter;
import com.example.zigzag.src.dress.dressall.interfaces.DressAllFragmentView;
import com.example.zigzag.src.itemdetail.ItemDetailActivity;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import java.util.ArrayList;

public class DressAllFragment extends Fragment implements MyRecyclerItemsViewAdapter.OnItemClickListener, DressAllFragmentView {
    ViewGroup viewGroup;
    private RecyclerView mItemsRecyclerView;
    private MyRecyclerItemsViewAdapter mAdapter;
    private ArrayList<ItemsResponse.ItemsResult> mItemList = new ArrayList<ItemsResponse.ItemsResult>();
    private DressAllService dressAllService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dressAllService = new DressAllService(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_dress_all, container, false);
        mItemList = new ArrayList<ItemsResponse.ItemsResult>();
        initView(viewGroup);

        getItemList();

        return viewGroup;
    }

    void initView(View view) {
        mItemsRecyclerView = viewGroup.findViewById(R.id.rv_dress_all);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        mItemsRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerItemsViewAdapter(mItemList, getContext());
        mAdapter.setOnItemClickListener(this);
        mItemsRecyclerView.setAdapter(mAdapter);
    }

    private void getItemList() {
        dressAllService.getItemList();
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

