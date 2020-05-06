package com.example.zigzag.src.home.shoppingmall.shoprank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;
import com.example.zigzag.src.home.shoppingmall.shoprank.models.ShopRankResponse;

import java.util.ArrayList;

public class ShopRankAdapter extends RecyclerView.Adapter<ShopRankAdapter.ViewHolder> {
    private ArrayList<ShopRankResponse.ShopRankResult> mItemList;
    private Context mContext;
    private LayoutInflater mInflate;
    private OnItemClickListener mOnItemClickListener = null;

    public ShopRankAdapter(ArrayList<ShopRankResponse.ShopRankResult> mItemList, Context mContext) {
        this.mItemList = mItemList;
        this.mInflate = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.recycler_shop_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ShopRankResponse.ShopRankResult item = mItemList.get(position);

//        String url = item.getImage_url().;
//        Glide.with(mContext).load(url).thumbnail(0.5f).into(holder.mShopPhoto);
        holder.mShopPhoto.setImageResource(R.drawable.image_shop_default);
        if(item.getIs_favorite().equals("Y")){
            holder.mShopLike.setImageResource(R.drawable.ic_shop_check_press);
        }else{
            holder.mShopLike.setImageResource(R.drawable.ic_null);
        }
        holder.mShopName.setText(item.getMall_name());
        holder.mShopRank.setText(item.getMall_rank());
        if(item.getTags().size() > 0){
            holder.mShopTag.setText(item.getTags().get(0).getTag_name());
        }else{
            holder.mShopTag.setText("");
        }
        holder.mRelativeItem.setOnClickListener(new View.OnClickListener() {
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

    public interface OnItemClickListener{
        void onItemClick(View view, ShopRankResponse.ShopRankResult item);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout mRelativeItem;
        private ImageView mShopPhoto;
        private TextView mShopRank, mShopName, mShopTag;
        private ImageView mShopLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRelativeItem = itemView.findViewById(R.id.linear_shop_item);
            mShopPhoto = itemView.findViewById(R.id.shop_photo);
            mShopRank = itemView.findViewById(R.id.tv_shopRank);
            mShopName = itemView.findViewById(R.id.item_shop_name);
            mShopTag = itemView.findViewById(R.id.item_shop_tag);
            mShopLike = itemView.findViewById(R.id.item_check_like);
        }
    }

}
