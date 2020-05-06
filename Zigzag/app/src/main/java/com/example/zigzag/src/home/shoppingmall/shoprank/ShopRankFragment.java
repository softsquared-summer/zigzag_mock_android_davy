package com.example.zigzag.src.home.shoppingmall.shoprank;

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
import com.example.zigzag.src.home.shoppingmall.shoprank.interfaces.ShopRankFragmentView;
import com.example.zigzag.src.home.shoppingmall.shoprank.models.ShopRankResponse;

import java.util.ArrayList;

public class ShopRankFragment extends Fragment implements ShopRankAdapter.OnItemClickListener,ShopRankFragmentView {
    ViewGroup viewGroup;
    private ArrayList<ShopRankResponse.ShopRankResult> mShopItemList;
    private ShopRankAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ShopRankService mShopRankService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShopRankService = new ShopRankService(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_shoprank,container,false);
        mShopItemList = new ArrayList<ShopRankResponse.ShopRankResult>();
        mRecyclerView = viewGroup.findViewById(R.id.rv_shop_rank);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ShopRankAdapter(mShopItemList, getContext());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        getShopItemList();

        return viewGroup;
    }

    public void getShopItemList(){
        mShopRankService.getShopRank();
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getShopRankSuccess(boolean isSuccess, int code, String message, ArrayList<ShopRankResponse.ShopRankResult> shopRankResults) {
        if(isSuccess){
            mShopItemList.clear();
            mShopItemList.addAll(shopRankResults);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(View view, ShopRankResponse.ShopRankResult item) {
        System.out.println(item.getMall_name());
    }
}
