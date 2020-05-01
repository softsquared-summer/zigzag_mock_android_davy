package com.example.zigzag.src.home.zigzag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclerViewHolder> {
    private ArrayList<MyData> mDataset;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemPhoto;
        public TextView itemShopName, itemInfo, itemPrice;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPhoto = (ImageView)itemView.findViewById(R.id.item_photo);
            itemShopName = (TextView)itemView.findViewById(R.id.item_shop_name);
            itemInfo = itemView.findViewById(R.id.item_info);
            itemPrice = itemView.findViewById(R.id.item_price);
        }
    }

    public MyRecyclerViewAdapter(ArrayList<MyData> myData){
        this.mDataset = myData;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        holder.itemPhoto.setImageResource(mDataset.get(position).getItemPhoto());
        holder.itemShopName.setText(mDataset.get(position).getShopName());
        holder.itemInfo.setText(mDataset.get(position).getItemInfo());
        holder.itemPrice.setText(mDataset.get(position).getItemPrice());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
