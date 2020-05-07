package com.example.zigzag.src;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zigzag.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener{

    private ImageView mBtnBuyDir, mBtnBuyBucket;

    public static BottomSheetDialog getInstance(){
        return new BottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        mBtnBuyDir = view.findViewById(R.id.item_detail_buy_dir);
        mBtnBuyBucket = view.findViewById(R.id.item_detail_buy_bucket);

        mBtnBuyDir.setOnClickListener(this);
        mBtnBuyBucket.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_detail_buy_dir:
                Toast.makeText(getContext(),"바로구매",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_detail_buy_bucket:
                Toast.makeText(getContext(), "장바구니에 담겼습니다",Toast.LENGTH_SHORT).show();
        }
        dismiss();
    }
}
