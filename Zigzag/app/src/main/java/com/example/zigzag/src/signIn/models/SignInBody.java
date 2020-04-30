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
    private Boolean is_over_14;

    @SerializedName("is_service_agree")
    private Boolean is_service_agree;

    @SerializedName("is_privacy_agree")
    private Boolean is_privacy_agree;

    @SerializedName("is_alarm_agree")
    private Boolean is_alarm_agree;

    public SignInBody(String email, String password, String phone, Boolean is_over_14, Boolean is_service_agree, Boolean is_privacy_agree, Boolean is_alarm_agree) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.is_over_14 = is_over_14;
        this.is_service_agree = is_service_agree;
        this.is_privacy_agree = is_privacy_agree;
        this.is_alarm_agree = is_alarm_agree;
    }
}
