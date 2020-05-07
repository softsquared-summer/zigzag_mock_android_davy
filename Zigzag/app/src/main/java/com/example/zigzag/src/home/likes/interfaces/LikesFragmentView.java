package com.example.zigzag.src.home.likes.interfaces;

import com.example.zigzag.src.itemdetail.models.ItemResponse;

import java.util.ArrayList;

public interface LikesFragmentView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getItemSuccess(boolean isSuccess, int code, String message, ArrayList<ItemResponse.ItemResult> itemsResult);
}
