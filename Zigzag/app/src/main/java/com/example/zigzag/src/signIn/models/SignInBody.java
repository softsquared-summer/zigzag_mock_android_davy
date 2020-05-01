package com.example.zigzag.src.signIn.models;

import com.google.gson.annotations.SerializedName;

public class SignInBody {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("phone")
    private String phone;

    @SerializedName("is_over_14")
    private String is_over_14;

    @SerializedName("is_service_agree")
    private String is_service_agree;

    @SerializedName("is_privacy_agree")
    private String is_privacy_agree;

    @SerializedName("is_alarm_agree")
    private String is_alarm_agree;

    public SignInBody(String email, String password, String phone, String is_over_14, String is_service_agree, String is_privacy_agree, String is_alarm_agree) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.is_over_14 = is_over_14;
        this.is_service_agree = is_service_agree;
        this.is_privacy_agree = is_privacy_agree;
        this.is_alarm_agree = is_alarm_agree;
    }
}
