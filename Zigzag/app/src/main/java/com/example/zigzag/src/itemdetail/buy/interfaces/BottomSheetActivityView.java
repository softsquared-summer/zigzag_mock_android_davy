package com.example.zigzag.src.itemdetail.buy.interfaces;

public interface BottomSheetActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void bucketSuccess(boolean isSuccess, int code, String message);

//    void getItemDetailSuccess(boolean isSuccess, int code,String message, ItemResponse.ItemResult itemResponse);
}
