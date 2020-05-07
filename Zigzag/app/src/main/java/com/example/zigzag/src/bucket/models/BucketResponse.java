package com.example.zigzag.src.bucket.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BucketResponse {

    public class BucketResult {

        @SerializedName("num")
        private int num;

        @SerializedName("list")
        private ArrayList<BucketItem> list;

        public int getNum() {
            return num;
        }

        public ArrayList<BucketItem> getList() {
            return list;
        }


        public class BucketItem {

            @SerializedName("item_id")
            private int item_id;

            @SerializedName("mall_name")
            private String mall_name;

            @SerializedName("item_name")
            private String item_name;

            @SerializedName("image")
            private ImageList image;

            public class ImageList {
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

            @SerializedName("size")
            private String size;

            @SerializedName("color")
            private String color;

            @SerializedName("num")
            private int num;

            @SerializedName("price")
            private String price;

            @SerializedName("ship")
            private String ship;

            public int getItem_id() {
                return item_id;
            }

            public String getMall_name() {
                return mall_name;
            }

            public String getItem_name() {
                return item_name;
            }

            public ImageList getImage() {
                return image;
            }

            public String getSize() {
                return size;
            }

            public String getColor() {
                return color;
            }

            public int getNum() {
                return num;
            }

            public String getPrice() {
                return price;
            }

            public String getShip() {
                return ship;
            }
        }
    }

    @SerializedName("result")
    private ArrayList<BucketResult> bucketResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("is_success")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public ArrayList<BucketResult> getBucketResult() {
        return bucketResult;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}