package com.example.zigzag.src.bucket.interfaces;


import com.example.zigzag.src.bucket.models.BucketResponse;

import java.util.ArrayList;

public interface BucketActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getBucketSuccess(boolean isSuccess, int code, String message, int count, ArrayList<BucketResponse.BucketResult.BucketItem> bucketResult);

//    void deleteBucketSuccess(Boolean isSuccess, int code, String message, )
}
