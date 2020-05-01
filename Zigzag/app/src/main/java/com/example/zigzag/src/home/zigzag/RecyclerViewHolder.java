package com.example.zigzag.src.home.zigzag;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private ImageView ItemPhoto;
    private TextView ItemShopName, ItemInfo, ItemPrice;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        ItemPhoto = itemView.findViewById(R.id.item_photo);
        ItemShopName = itemView.findViewById(R.id.item_shop_name);
        ItemInfo = itemView.findViewById(R.id.item_info);
        ItemPrice = itemView.findViewById(R.id.item_price);
    }

}
