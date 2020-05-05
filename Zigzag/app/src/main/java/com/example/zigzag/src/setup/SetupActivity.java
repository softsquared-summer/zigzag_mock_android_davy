package com.example.zigzag.src.setup;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.CustomDialog;
import com.example.zigzag.src.setup.interfaces.SetupActivityView;
import com.example.zigzag.src.withdrawal.WithdrawalActivity;

import java.util.Objects;

public class SetupActivity extends BaseActivity implements SetupActivityView {

    private LinearLayout mBtnLogout;
    private TextView mTvWithdrawal;
    private Toolbar mToolbar;

    private CustomDialog customDialog;
    private SetupService setupService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        setupService = new SetupService(this);

        mBtnLogout = findViewById(R.id.linear_setup_logout);
        mTvWithdrawal = findViewById(R.id.tv_setup_withdrawal);
        mTvWithdrawal.setPaintFlags(mTvWithdrawal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        mToolbar = findViewById(R.id.setup_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        mTvWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupActivity.this, WithdrawalActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog = new CustomDialog(SetupActivity.this);
                customDialog.setCancelable(true);
                customDialog.getWindow().setGravity(Gravity.BOTTOM);
                customDialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void tryGetTest() {
        showProgressDialog();
        setupService.getTest();
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

//    @Override
//    public void logInSuccess(LogInResponse.LogInResult logInResult) {
//        hideProgressDialog();
//        MypageFragment fragment = new MypageFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
////        Intent intent = new Intent(this, MypageFragment.class);
//////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//////        startActivity(intent);
//    }

    public void customOnClick(View view) {
        switch (view.getId()) {
//            case R.id.iv_login_back:
//                finish();
//                showCustomToast("뒤로");
//            default:
//                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
