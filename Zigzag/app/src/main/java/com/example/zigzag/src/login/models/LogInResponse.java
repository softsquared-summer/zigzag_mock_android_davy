package com.example.zigzag.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LogInResponse {

    public class LogInResult {
        @SerializedName("jwt")
        private String jwt;

        public String getJwt() {
            return jwt;
        }
    }
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private LogInResult logInResult;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public LogInResult getLogInResult() {
        return logInResult;
    }
}