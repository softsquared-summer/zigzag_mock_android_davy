package com.example.zigzag.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LogInBody {

    @SerializedName("id")
    private String id;

    @SerializedName("pw")
    private String pw;

    public LogInBody(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
