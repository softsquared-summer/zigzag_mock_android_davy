package com.example.zigzag.src.home.likes.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemsResponse {

    public class ItemsResult {
        @SerializedName("num")
        private int num;

        @SerializedName("list")
        private ArrayList<LikesItemList> likesItemsResults;

        public class LikesItemList{
            @SerializedName("item_id")
            private int item_id;

            @SerializedName("image")
            private ImageUrl image;

            @SerializedName("is_free_ship")
            private String is_free_ship;

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

            public String getIs_free_ship() {
                return is_free_ship;
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

        public ArrayList<LikesItemList> getLikesItemsResults() {
            return likesItemsResults;
        }


        public int getNum() {
            return num;
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