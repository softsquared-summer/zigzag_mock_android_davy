package com.example.zigzag.src.home.zigzag;

public class MyData {
    public int itemPhoto;
    public String shopName, itemInfo, itemPrice;

    public MyData(int itemPhoto, String shopName, String itemInfo, String itemPrice) {
        this.itemPhoto = itemPhoto;
        this.shopName = shopName;
        this.itemInfo = itemInfo;
        this.itemPrice = itemPrice;
    }

    public int getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(int itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
