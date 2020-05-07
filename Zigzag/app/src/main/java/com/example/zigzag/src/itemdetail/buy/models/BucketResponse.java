package com.example.zigzag.src.itemdetail.buy.models;

import com.google.gson.annotations.SerializedName;

public class BucketResponse {

    @SerializedName("result")
    private String bucketResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean is_success;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getBucketResult() {
        return bucketResult;
    }

    public boolean is_success() {
        return is_success;
    }

}