package com.example.zigzag.src.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;
import com.example.zigzag.src.bucket.models.BucketResponse;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private ArrayList<BucketResponse.BucketResult.BucketItem> mItemList;
    private LayoutInflater mInflate;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;

    public OrderAdapter(ArrayList<BucketResponse.BucketResult.BucketItem> mItemList, Context mContext) {
        this.mItemList = mItemList;
        this.mInflate = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, BucketResponse.BucketResult.BucketItem item);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = mInflate.inflate(R.layout.recycler_bucket_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {

        final BucketResponse.BucketResult.BucketItem item = mItemList.get(position);


//        String url = item.getImage().getImage_url1();
//        Glide.with(mContext).load(url).thumbnail(0.5f).into(holder.mItemPhoto);
        holder.mImage.setImageResource(R.drawable.image_bucket_default);
        holder.mShopName.setText(item.getMall_name());
        holder.mProductName.setText(item.getItem_name());
        holder.mPrice.setText(item.getPrice()+"원");
        holder.mOption.setText(item.getColor()+"/"+item.getSize());
    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mProductName,mPrice,mShopName;
        private ImageView mImage;
        private TextView mOption;

        public ViewHolder(View convertView) {
            super(convertView);

            mShopName = convertView.findViewById(R.id.bucket_shop_name);
            mProductName = (TextView) convertView.findViewById(R.id.bucket_item_name);
            mPrice = (TextView) convertView.findViewById(R.id.bucket_item_price);
            mImage = (ImageView) convertView.findViewById(R.id.bucket_item_photo);
            mOption = (TextView) convertView.findViewById(R.id.bucket_item_option);
        }
    }
}
