package com.example.zigzag.src.home.shoppingmall.shoprank.interfaces;

import com.example.zigzag.src.home.shoppingmall.shoprank.models.ShopRankResponse;

import java.util.ArrayList;

public interface ShopRankFragmentView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getShopRankSuccess(boolean isSuccess, int code, String message, ArrayList<ShopRankResponse.ShopRankResult> shopRankResults);
}
