package com.example.zigzag.src.home.zigzag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        public CheckBox itemLike;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPhoto = (ImageView)itemView.findViewById(R.id.item_photo);
            itemShopName = (TextView)itemView.findViewById(R.id.item_shop_name);
            itemInfo = itemView.findViewById(R.id.item_info);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemLike = itemView.findViewById(R.id.item_check_like);
        }
    }

    public MyRecyclerViewAdapter(ArrayList<MyData> myData){
        this.mDataset = myData;
    }

    public boolean isChecked(int position){
        return mDataset.get(position).checked;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {


        holder.itemPhoto.setImageResource(mDataset.get(position).getItemPhoto());
        holder.itemShopName.setText(mDataset.get(position).getShopName());
        holder.itemInfo.setText(mDataset.get(position).getItemInfo());
        holder.itemPrice.setText(mDataset.get(position).getItemPrice());
        holder.itemLike.setChecked(mDataset.get(position).checked);
        holder.itemLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //체크박스 체크 후 상태 저장하기
                boolean newState = !mDataset.get(position).isChecked();
                mDataset.get(position).setChecked(newState);
                holder.itemLike.setChecked(isChecked(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
