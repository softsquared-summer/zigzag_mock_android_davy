package com.example.zigzag.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LogInResponse {

//    public class LogInResult {
//        @SerializedName("jwt")
//        private String jwt;
//
//        public String getJwt() {
//            return jwt;
//        }
//    }

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("is_success")
    private boolean is_success;

    @SerializedName("result")
    private String result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return is_success;
    }

    public String getResult() {
        return result;
    }
}