package com.example.zigzag.src.bucket;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.bucket.interfaces.BucketActivityView;
import com.example.zigzag.src.bucket.models.BucketResponse;
import com.example.zigzag.src.order.OrderActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class BucketActivity extends BaseActivity implements BucketActivityView {

    private RecyclerView mRvBucket;
    private ArrayList<BucketResponse.BucketResult.BucketItem> mBucketList =new ArrayList<>();
    private BucketAdapter mBucketAdapter;
    private TextView mBtnBuy, mTvTotalPrice, mTvTotalShip, mTvAllTotal;
    private String mAllTotal, mAllShip, mItemTotal;
    private BucketService bucketService;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);
        bucketService = new BucketService(this);

        mToolbar = findViewById(R.id.bucket_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_x);
        getSupportActionBar().setIcon(R.drawable.bucket);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mBtnBuy = findViewById(R.id.btn_bucket_item_buy);

        mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("price", mAllTotal);
                startActivity(intent);
            }
        });

        initView();

        getBucketList();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getBucketList(){
        bucketService.getBucketList();
    }

    public void initView(){
        mRvBucket = findViewById(R.id.rv_bucket);
        mTvTotalPrice = findViewById(R.id.bucket_item_total);
        mTvTotalShip = findViewById(R.id.bucket_item_ship_total);
        mTvAllTotal = findViewById(R.id.bucket_item_all_price);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRvBucket.setLayoutManager(mLayoutManager);
        mBucketAdapter = new BucketAdapter(mBucketList,getApplicationContext());
        mRvBucket.setAdapter(mBucketAdapter);
    }


    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void getBucketSuccess(boolean is_success, int code, String message, int count, ArrayList<BucketResponse.BucketResult.BucketItem> bucketResult) {
        if(is_success){
            mBucketList.clear();
            mBucketList.addAll(bucketResult);
            mBucketAdapter.notifyDataSetChanged();
            countPrice(mBucketList);

            System.out.println("아이템 리스트 조회" + mBucketList.toString());
        }
    }

    private void countPrice(ArrayList<BucketResponse.BucketResult.BucketItem> mBucketList ) {
        int price=0;
        int shipPrice = 0;
        int allPrice = 0;

        for(int i = 0; i< this.mBucketList.size(); i++){
            String rPrice= this.mBucketList.get(i).getPrice().replace(",","");
            price += Integer.parseInt(rPrice);
        }
        for(int i = 0; i < this.mBucketList.size(); i++){
            String rShip = this.mBucketList.get(i).getShip().replace(",","");
            shipPrice += Integer.parseInt(rShip);
        }

        allPrice = price + shipPrice;

        DecimalFormat formatter=new DecimalFormat("###,###");
        mItemTotal = formatter.format(price);
        mAllShip = formatter.format(shipPrice);
        mAllTotal = formatter.format(allPrice);
        mTvTotalShip.setText(mAllShip+"원");
        mBtnBuy.setText("총 "+mAllTotal+" 원구매하기");
        mTvAllTotal.setText(mAllTotal+"원");
        mTvTotalPrice.setText(mItemTotal+"원");

    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_hello_world:
            default:
                break;
        }
    }
}
