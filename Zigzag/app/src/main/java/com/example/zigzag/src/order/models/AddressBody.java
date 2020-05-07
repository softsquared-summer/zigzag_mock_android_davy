package com.example.zigzag.src.order.models;

import com.google.gson.annotations.SerializedName;

public class AddressBody {

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("zipcode")
    private int zipcode;

    @SerializedName("address")
    private String address;

    @SerializedName("address_detail")
    private String address_detail;

    @SerializedName("memo")
    private String memo;

    public AddressBody(String name, String phone, int zipcode, String address, String address_detail, String memo) {
        this.name = name;
        this.phone = phone;
        this.zipcode = zipcode;
        this.address = address;
        this.address_detail = address_detail;
        this.memo = memo;
    }
}
