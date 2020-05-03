package com.example.zigzag.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LogInBody {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LogInBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
