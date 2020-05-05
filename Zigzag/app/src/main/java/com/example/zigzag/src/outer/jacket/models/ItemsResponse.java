package com.example.zigzag.src.outer.jacket.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemsResponse {

    public class ItemsResult {
        @SerializedName("item_id")
        private int item_id;

        @SerializedName("item_category")
        private String item_category;

        @SerializedName("item_category_detail")
        private String item_category_detail;

        @SerializedName("image")
        private ImageUrl image;

        @SerializedName("is_free_ship")
        private String is_free_ship;

        @SerializedName("is_heart")
        private String is_heart;

        @SerializedName("mall_name")
        private String mall_name;

        @SerializedName("item_name")
        private String item_name;

        @SerializedName("discount")
        private String discount;

        @SerializedName("price")
        private String price;

        public ImageUrl getImage() {
            return image;
        }

        public int getItem_id() {
            return item_id;
        }

        public String getItem_category() {
            return item_category;
        }

        public String getItem_category_detail() {
            return item_category_detail;
        }

        public String getIs_free_ship() {
            return is_free_ship;
        }

        public String getIs_heart() {
            return is_heart;
        }

        public String getMall_name() {
            return mall_name;
        }

        public String getItem_name() {
            return item_name;
        }

        public String getDiscount() {
            return discount;
        }

        public String getPrice() {
            return price;
        }

        public class ImageUrl{
            @SerializedName("image_url1")
            private String image_url1;

            @SerializedName("image_url2")
            private String image_url2;

            public String getImage_url1() {
                return image_url1;
            }

            public String getImage_url2() {
                return image_url2;
            }
        }
    }

    @SerializedName("result")
    private ArrayList<ItemsResult> itemsResults;

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

    public ArrayList<ItemsResult> getItemsResults() {
        return itemsResults;
    }
}