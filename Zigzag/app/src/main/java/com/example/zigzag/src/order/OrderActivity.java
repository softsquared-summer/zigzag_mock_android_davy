package com.example.zigzag.src.order;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.bucket.models.BucketResponse;
import com.example.zigzag.src.order.interfaces.OrderActivityView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class OrderActivity extends BaseActivity implements OrderActivityView {

    private RecyclerView mRvBucket;
    private ArrayList<BucketResponse.BucketResult.BucketItem> mBucketList =new ArrayList<>();
    private BucketAdapter mBucketAdapter;
    private TextView mBtnBuy, mTvTotalPrice, mTvTotalShip, mTvAllTotal;
    private String mItemTotal,mAllTotal, mAllShip;
    private OrderService orderService;
    private Toolbar mToolbar;

    private EditText mEtName, mEtPhone, mEtZipCode, mEtAddress, mEtAddressDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderService = new OrderService(this);

        mToolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_x);
        getSupportActionBar().setIcon(R.drawable.bucket);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mEtName = findViewById(R.id.order_user_name);
        mEtPhone = findViewById(R.id.order_user_phone);
        mEtZipCode = findViewById(R.id.order_user_zipcode);
        mEtAddress = findViewById(R.id.order_user_address);
        mEtAddressDetail = findViewById(R.id.order_user_address_detail);

        mBtnBuy = findViewById(R.id.btn_order_item_buy);

        mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tryPostAddress();
            }
        });

        initView();

        getBucketList();
    }

    private void tryPostAddress(){
        showProgressDialog();
        //logInService.postLogIn("bige4739@gmail.com","1234");
        orderService.postAddress(mEtName.getText().toString(),mEtPhone.getText().toString(),Integer.parseInt(mEtZipCode.getText().toString()),mEtAddress.getText().toString(),mEtAddressDetail.getText().toString(),"");
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
        orderService.getBucketList();
    }

    public void initView(){
        mRvBucket = findViewById(R.id.rv_order);
        mTvTotalPrice = findViewById(R.id.order_item_total);
        mTvTotalShip = findViewById(R.id.order_item_ship_total);
        mTvAllTotal = findViewById(R.id.order_item_all_price);
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

        }
    }

    @Override
    public void postAddressSuccess(boolean isSuccess, int code, String message) {
        hideProgressDialog();
        if(isSuccess) {
            showCustomToast("주소지 등록 성공");
        }else{
            showCustomToast("주소지 등록 실패");
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
        mTvTotalPrice.setText(mItemTotal+"원");
        mTvAllTotal.setText(mAllTotal+"원");

    }

    public void customOnClick(View view) {
        switch (view.getId()) {
//            case R.id.:
            default:
                break;
        }
    }
}
