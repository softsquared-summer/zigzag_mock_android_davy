package com.example.zigzag.src.order.models;

import com.google.gson.annotations.SerializedName;

public class PaymentBody {

    @SerializedName("is_over_14")
    private String is_over_14;

    @SerializedName("is_service_agree")
    private String is_service_agree;

    @SerializedName("is_order_agree")
    private String is_order_agree;

    @SerializedName("item_id1")
    private int item_id1;

    @SerializedName("item_id2")
    private int item_id2;

    @SerializedName("item_id3")
    private int item_id3;

    @SerializedName("item_id4")
    private int item_id4;

    @SerializedName("item_id5")
    private int item_id5;

    public PaymentBody(String is_over_14, String is_service_agree, String is_order_agree, int item_id1, int item_id2, int item_id3, int item_id4, int item_id5) {
        this.is_over_14 = is_over_14;
        this.is_service_agree = is_service_agree;
        this.is_order_agree = is_order_agree;
        this.item_id1 = item_id1;
        this.item_id2 = item_id2;
        this.item_id3 = item_id3;
        this.item_id4 = item_id4;
        this.item_id5 = item_id5;
    }
}
