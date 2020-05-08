package com.example.zigzag.src.home.likes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;
import com.example.zigzag.src.bucket.BucketActivity;
import com.example.zigzag.src.home.likes.interfaces.LikesFragmentView;
import com.example.zigzag.src.home.likes.models.ItemsResponse;

import java.util.ArrayList;

public class LikesFragment extends Fragment implements MyRecyclerItemsViewAdapter.OnItemClickListener, LikesFragmentView {
    ViewGroup viewGroup;
    private RecyclerView mItemsRecyclerView;
    private MyRecyclerItemsViewAdapter mAdapter;
    private ArrayList<ItemsResponse.ItemsResult> mItemList;
    private LikesService mLikesService;
    private ImageView mBtnBucket;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLikesService = new LikesService(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_likes,container,false);
        mItemList = new ArrayList<ItemsResponse.ItemsResult>();
        initView(viewGroup);

        mBtnBucket = viewGroup.findViewById(R.id.likes_bucket);
        mBtnBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BucketActivity.class);
                startActivity(intent);
            }
        });

        getItemList();

        return viewGroup;
    }

    void initView(View view) {
        mItemsRecyclerView = viewGroup.findViewById(R.id.rv_likes);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        mItemsRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerItemsViewAdapter(mItemList, getContext());
        mAdapter.setOnItemClickListener(this);
        mItemsRecyclerView.setAdapter(mAdapter);
    }

    private void getItemList() {
        mLikesService.getItemList();
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
        System.out.println(item.getLikesItemsResults().get(0).getItem_name());
//        Intent intent = new Intent(getContext(),ItemDetail.class )
//        intent.putExtra("item_id",item.getmItemId());
//        startActivity(intent);
    }

}
