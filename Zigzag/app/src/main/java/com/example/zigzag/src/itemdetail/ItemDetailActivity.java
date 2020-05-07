package com.example.zigzag.src.itemdetail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.BottomSheetDialog;
import com.example.zigzag.src.itemdetail.interfaces.ItemDetailActivityView;
import com.example.zigzag.src.itemdetail.models.ItemResponse;

import java.util.Objects;

public class ItemDetailActivity extends BaseActivity implements ItemDetailActivityView {
    private Toolbar mToolbar;
    private LinearLayout mBottomSheet;
    private ImageView mBtnBuy;
    private ItemResponse.ItemResult mItem;
    private int mItemNum;
    private TextView mTvReviewNum, mTvStoreName, mTvItemName, mTvItemPrice, mTvDiscount, mTvItemCode;
    private CheckBox mItemLike, mShopFavorite;
    private ImageView mItemPhoto;
    private ItemDetailService itemDetailService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetail);


        mToolbar = findViewById(R.id.item_detail_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_x);
        getSupportActionBar().setIcon(R.drawable.ic_shop);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mBtnBuy = findViewById(R.id.item_detail_buy);

        mBtnBuy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.getInstance();
                bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
            }
        });

        initView();
        setItemDetail();

    }

    public void getItemDetail(int number){
        itemDetailService.getItemDetail(number);
    }
    public void setItemDetail(){
        if(mItemNum != 0){
            getItemDetail(mItemNum);
        }else {
            finish();
        }
    }
    private void initView(){
        mItemNum = getIntent().getIntExtra("item_id",0);
        System.out.println("아이템 아이디"+mItemNum);
        itemDetailService = new ItemDetailService(this);

        mTvReviewNum = findViewById(R.id.item_detail_review_num);
        mTvDiscount = findViewById(R.id.item_detail_discount);
        mTvItemName = findViewById(R.id.item_detail_item_name);
        mTvItemPrice = findViewById(R.id.item_detail_price);
        mItemPhoto = findViewById(R.id.item_detail_photo);
        mItemLike = findViewById(R.id.item_detail_item_like);
        mShopFavorite = findViewById(R.id.item_detail_favorite);
        mTvStoreName = findViewById(R.id.item_detail_shop_name);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bucket, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.toolbar_bucket_button:
                showCustomToast("장바구니");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getItemDetailSuccess(boolean isSuccess, int code, String message, ItemResponse.ItemResult itemResponse) {
        if(isSuccess){
            mTvItemPrice.setText(itemResponse.getPrice()+"원");
            mTvItemName.setText(itemResponse.getItem_name());
            mTvStoreName.setText(itemResponse.getMall_name());
            mTvDiscount.setText(itemResponse.getDiscount());
            mTvReviewNum.setText("("+itemResponse.getComment_num()+")");
            mItemPhoto.setImageResource(R.drawable.image_item_detail_photo);
            if(itemResponse.getIs_heart().equals("Y")){
                mItemLike.setChecked(true);
            }
        }
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

    public void customOnClick(View view) {
        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
//            default:
//                break;
        }
    }
}
