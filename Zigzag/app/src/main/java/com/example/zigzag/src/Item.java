package com.example.zigzag.src;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{

    private int mItemId;
    private String mItemCategory;
    private String mItemCategoryDetail;
    private String mImageUrl_1;
    private String mImageUrl_2;
    private String mFreeShip;
    private String mHeart;
    private String mShopName;
    private String mItemName;
    private String mDiscount;
    private String mPrice;

    public Item(int mItemId, String mItemCategory, String mItemCategoryDetail, String mImageUrl_1, String mImageUrl_2, String mFreeShip, String mHeart, String mShopName, String mItemName, String mDiscount, String mPrice) {
        this.mItemId = mItemId;
        this.mItemCategory = mItemCategory;
        this.mItemCategoryDetail = mItemCategoryDetail;
        this.mImageUrl_1 = mImageUrl_1;
        this.mImageUrl_2 = mImageUrl_2;
        this.mFreeShip = mFreeShip;
        this.mHeart = mHeart;
        this.mShopName = mShopName;
        this.mItemName = mItemName;
        this.mDiscount = mDiscount;
        this.mPrice = mPrice;
    }

    protected Item(Parcel in) {
        mItemId = in.readInt();
        mItemCategory = in.readString();
        mItemCategoryDetail = in.readString();
        mImageUrl_1 = in.readString();
        mImageUrl_2 = in.readString();
        mFreeShip = in.readString();
        mHeart = in.readString();
        mShopName = in.readString();
        mItemName = in.readString();
        mDiscount = in.readString();
        mPrice = in.readString();
    }


    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getmItemId() {
        return mItemId;
    }

    public String getmItemCategory() {
        return mItemCategory;
    }

    public String getmItemCategoryDetail() {
        return mItemCategoryDetail;
    }

    public String getmImageUrl_1() {
        return mImageUrl_1;
    }

    public String getmImageUrl_2() {
        return mImageUrl_2;
    }

    public String getmFreeShip() {
        return mFreeShip;
    }

    public String getmHeart() {
        return mHeart;
    }

    public String getmShopName() {
        return mShopName;
    }

    public String getmItemName() {
        return mItemName;
    }

    public String getmDiscount() {
        return mDiscount;
    }

    public String getmPrice() {
        return mPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mItemId);
        dest.writeString(mItemCategory);
        dest.writeString(mItemCategoryDetail);
        dest.writeString(mImageUrl_1);
        dest.writeString(mImageUrl_2);
        dest.writeString(mFreeShip);
        dest.writeString(mHeart);
        dest.writeString(mShopName);
        dest.writeString(mItemName);
        dest.writeString(mDiscount);
        dest.writeString(mPrice);
    }
}
