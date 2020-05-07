package com.example.zigzag.src.itemdetail.buy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zigzag.R;
import com.example.zigzag.src.itemdetail.buy.interfaces.BottomSheetActivityView;
import com.example.zigzag.src.itemdetail.models.ItemResponse;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener, BottomSheetActivityView {

    private ImageView mBtnBuyDir, mBtnBuyBucket, mBtnPlus, mBtnMinus;
    private TextView mItemName, mItemPrice, mItemNum;
    private int mItemCount = 1;
    private ItemResponse.ItemResult mItem;
    private BottomSheetService bottomSheetService;
    private Context context;

    public static BottomSheetDialog getInstance() {

        return new BottomSheetDialog();
    }

    public void setMItem(ItemResponse.ItemResult item) {
        mItem = item;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        mBtnBuyDir = view.findViewById(R.id.item_detail_buy_dir);
        mBtnBuyBucket = view.findViewById(R.id.item_detail_buy_bucket);
        mBtnPlus = view.findViewById(R.id.buy_dialog_plus);
        mBtnMinus = view.findViewById(R.id.buy_dialog_minus);


        mBtnBuyDir.setOnClickListener(this);
        mBtnBuyBucket.setOnClickListener(this);
        mBtnMinus.setOnClickListener(this);
        mBtnPlus.setOnClickListener(this);

        mItemName = view.findViewById(R.id.buy_dialog_item_name);
        mItemPrice = view.findViewById(R.id.buy_dialog_item_price);
        mItemNum = view.findViewById(R.id.buy_dialog_item_num);

//        mItemPrice.setText(String.valueOf(mItem.getPrice())+"원");
//        mItemName.setText(mItem.getItem_name());

        bottomSheetService = new BottomSheetService(this);

        return view;
    }

    public void updateCount(){
        mItemNum.setText(String.valueOf(mItemCount));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_detail_buy_dir:
                Toast.makeText(getContext(), "바로구매", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_detail_buy_bucket:
                int item_id = mItem.getItem_id();
                String color = "black";
                String size = "L";
                int num = mItemCount;
                bottomSheetService.postBucket(item_id, color, size, num);
                //Toast.makeText(getContext(), "장바구니에 담겼습니다",Toast.LENGTH_SHORT).show();
                break;
            case R.id.buy_dialog_minus:
                if (mItemCount > 1) {
                    mItemCount--;
                    updateCount();
                    System.out.println("-");
                } else {
                    Toast.makeText(getContext(), "1개 이상 담아야 합니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.buy_dialog_plus:
                if (mItemCount < 5) {
                    mItemCount++;
                    updateCount();
                    System.out.println("+");
                } else {
                    Toast.makeText(getContext(), "최대 수량을 초과했습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void bucketSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            Toast.makeText(getView().getContext(),"장바구니에 담겼습니다!",Toast.LENGTH_SHORT).show();
            System.out.println("성공");
        }else{
            Toast.makeText(getView().getContext(),"이미 담긴 항목입니다.", Toast.LENGTH_SHORT).show();
            System.out.println("실패");
        }
    }
}
