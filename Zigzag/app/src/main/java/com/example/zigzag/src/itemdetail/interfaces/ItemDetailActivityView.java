package com.example.zigzag.src.itemdetail.interfaces;

import com.example.zigzag.src.itemdetail.models.ItemResponse;

public interface ItemDetailActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getItemDetailSuccess(boolean isSuccess, int code,String message, ItemResponse.ItemResult itemResponse);
}
