package com.example.zigzag.src;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zigzag.R;
import com.example.zigzag.src.outer.all.models.OuterAllResponse;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclerViewHolders> {

//    private ArrayList<Item> mItemList;
    private ArrayList<OuterAllResponse.OuterAllResult> mItemList;
    private LayoutInflater mInflate;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;

    public MyRecyclerViewAdapter(ArrayList<OuterAllResponse.OuterAllResult> mItemList,Context mContext) {
        this.mItemList = mItemList;
        this.mInflate = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }


    public interface OnItemClickListener{
        void onItemClick(View view, OuterAllResponse.OuterAllResult item);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.recycler_item, parent, false);
        RecyclerViewHolders viewHolder = new RecyclerViewHolders(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.RecyclerViewHolders holder, int position) {
        final OuterAllResponse.OuterAllResult item = mItemList.get(position);

        String url = item.getImage().getImage_url1();
        Glide.with(mContext).load(url).thumbnail(0.5f).into(holder.mItemPhoto);
        if(item.getIs_heart().equals("Y")){
            holder.mItemLike.setChecked(true);
        }else{
            holder.mItemLike.setChecked(false);
        }
        holder.mItemShopName.setText(item.getMall_name());
        holder.mItemName.setText(item.getItem_name());
        holder.mItemDiscount.setText(item.getDiscount());
        holder.mItemPrice.setText(item.getPrice());
        holder.mLinearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder{
        private LinearLayout mLinearItem;
        private ImageView mItemPhoto;
        private CheckBox mItemLike;
        private TextView mItemShopName,mItemName,mItemDiscount,mItemPrice;


        public RecyclerViewHolders(@NonNull View itemView) {
            super(itemView);

            mLinearItem = itemView.findViewById(R.id.linear_item);
            mItemLike = itemView.findViewById(R.id.item_check_like);
            mItemPhoto = itemView.findViewById(R.id.item_photo);
            mItemShopName = itemView.findViewById(R.id.item_shop_name);
            mItemName = itemView.findViewById(R.id.item_info);
            mItemDiscount = itemView.findViewById(R.id.item_discount);
            mItemPrice = itemView.findViewById(R.id.item_price);
        }
    }
}
