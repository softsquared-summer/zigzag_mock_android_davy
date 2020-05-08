package com.example.zigzag.src.order.interfaces;


import com.example.zigzag.src.bucket.models.BucketResponse;

import java.util.ArrayList;

public interface OrderActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getBucketSuccess(boolean isSuccess, int code, String message, int count, ArrayList<BucketResponse.BucketResult.BucketItem> bucketResult);

    void postAddressSuccess(boolean isSuccess, int code, String message);

    void postPaymentSuccess(boolean isSuccess, int code, String message);
}
