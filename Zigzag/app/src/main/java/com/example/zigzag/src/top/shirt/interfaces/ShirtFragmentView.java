package com.example.zigzag.src.top.shirt.interfaces;

import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import java.util.ArrayList;

public interface ShirtFragmentView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getItemSuccess(boolean isSuccess, int code, String message, ArrayList<ItemsResponse.ItemsResult> outerAllResult);
}
