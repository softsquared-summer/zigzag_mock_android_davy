package com.example.zigzag.src.home.zigzag;

public class MyData {
    public int itemPhoto;
    public String shopName, itemInfo, itemPrice;
    public boolean checked;

    public MyData(int itemPhoto, String shopName, String itemInfo, String itemPrice, boolean b) {
        this.itemPhoto = itemPhoto;
        this.shopName = shopName;
        this.itemInfo = itemInfo;
        this.itemPrice = itemPrice;
        this.checked = b;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
