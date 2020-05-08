package com.example.zigzag.src.home.shoppingmall.shoplike.models;

import com.google.gson.annotations.SerializedName;

public class ShopLikesResponse {

    public class ShopLikeResult {
        @SerializedName("num")
        private String num;

        @SerializedName("list")
        private ShopLikeList list;

        public class ShopLikeList{
            @SerializedName("mall_id")
            private int mall_id;

            @SerializedName("image_url")
            private String image_url;

            @SerializedName("mall_name")
            private String mall_name;

            @SerializedName("tags")
            private String tags;

            public int getMall_id() {
                return mall_id;
            }

            public String getImage_url() {
                return image_url;
            }

            public String getMall_name() {
                return mall_name;
            }

            public String getTags() {
                return tags;
            }
        }

        public String getNum() {
            return num;
        }

        public ShopLikeList getList() {
            return list;
        }
    }

    @SerializedName("result")
    private ShopLikeResult result;

    @SerializedName("is_success")
    private boolean is_success;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;


    public boolean isSuccess() {
        return is_success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return is_success;
    }

    public ShopLikeResult getResult() {
        return result;
    }
}