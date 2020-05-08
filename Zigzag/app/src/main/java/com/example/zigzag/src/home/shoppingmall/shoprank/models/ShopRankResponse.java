package com.example.zigzag.src.home.shoppingmall.shoprank.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShopRankResponse {

    public class ShopRankResult {
        @SerializedName("mall_id")
        private int mall_id;

        @SerializedName("image_url")
        private String image_url;

        @SerializedName("is_favorite")
        private String is_favorite;

        @SerializedName("mall_name")
        private String mall_name;

        @SerializedName("mall_rank")
        private String mall_rank;

        @SerializedName("tags")
        private TagList tags;

        public class TagList{
            @SerializedName("tag_id")
            private String tag_id;
            @SerializedName("tag_name")
            private String tag_name;

            public String getTag_id() {
                return tag_id;
            }

            public String getTag_name() {
                return tag_name;
            }
        }
        public int getMall_id() {
            return mall_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public String getIs_favorite() {
            return is_favorite;
        }

        public String getMall_name() {
            return mall_name;
        }

        public String getMall_rank() {
            return mall_rank;
        }

        public TagList getTags() {
            return tags;
        }
    }

    @SerializedName("result")
    private ArrayList<ShopRankResult> shopRankResults;

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

    public ArrayList<ShopRankResult> getShopRankResults() {
        return shopRankResults;
    }
}