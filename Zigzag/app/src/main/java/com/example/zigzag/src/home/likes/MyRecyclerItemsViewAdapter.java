package com.example.zigzag.src.home.likes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;
import com.example.zigzag.src.home.likes.models.ItemsResponse;

import java.util.ArrayList;

public class MyRecyclerItemsViewAdapter extends RecyclerView.Adapter<MyRecyclerItemsViewAdapter.RecyclerViewHolders> {

//    private ArrayList<Item> mItemList;
    private ArrayList<ItemsResponse.ItemsResult> mItemList;
    private LayoutInflater mInflate;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;

    public MyRecyclerItemsViewAdapter(ArrayList<ItemsResponse.ItemsResult> mItemList, Context mContext) {
        this.mItemList = mItemList;
        this.mInflate = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }


    public interface OnItemClickListener{
        void onItemClick(View view, ItemsResponse.ItemsResult item);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.recycler_like_item, parent, false);
        RecyclerViewHolders viewHolder = new RecyclerViewHolders(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolders holder, final int position) {
        final ItemsResponse.ItemsResult item = mItemList.get(position);

//        String url = item.getImage().getImage_url1();
//        Glide.with(mContext).load(url).thumbnail(0.5f).into(holder.mItemPhoto);
        holder.mItemPhoto.setImageResource(R.drawable.image_default);

        holder.mItemShopName.setText(item.getLikesItemsResults().get(position).getMall_name());
        holder.mItemName.setText(item.getLikesItemsResults().get(position).getItem_name());
        holder.mItemDiscount.setText(item.getLikesItemsResults().get(position).getDiscount());
        holder.mItemPrice.setText(item.getLikesItemsResults().get(position).getPrice());
        holder.mLinearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, item);
            }
        });
        holder.mItemNum.setText("찜한 상품"+mItemList.size());
//        holder.mItemLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //체크박스 체크 후 상태 저장하기
//                boolean newState = !mItemList.get(position).getIs_heart();
//                mItemList.get(position).setChecked(newState);
//                holder.mItemLike.setChecked(isChecked(position));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder{
        private LinearLayout mLinearItem;
        private ImageView mItemPhoto;
        private TextView mItemShopName,mItemName,mItemDiscount,mItemPrice, mItemNum;

        public RecyclerViewHolders(@NonNull View itemView) {
            super(itemView);

            mLinearItem = itemView.findViewById(R.id.linear_like_item);
            mItemPhoto = itemView.findViewById(R.id.item_like_photo);
            mItemShopName = itemView.findViewById(R.id.item_like_shop_name);
            mItemName = itemView.findViewById(R.id.item_like_info);
            mItemDiscount = itemView.findViewById(R.id.item_like_discount);
            mItemPrice = itemView.findViewById(R.id.item_like_price);
            mItemNum = itemView.findViewById(R.id.tv_like_num);
        }
    }
}
