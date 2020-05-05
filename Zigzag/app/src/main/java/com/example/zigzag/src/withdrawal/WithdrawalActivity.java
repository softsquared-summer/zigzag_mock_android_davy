package com.example.zigzag.src.withdrawal;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.withdrawal.interfaces.WithdrawalActivityView;

public class WithdrawalActivity extends BaseActivity implements WithdrawalActivityView {


    private Toolbar mToolbar;
    private Button mBtnPositive, mBtnNegative;
    private WithdrawalService withdrawalService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        withdrawalService = new WithdrawalService(this);

        mBtnNegative = findViewById(R.id.withdrawal_negative);
        mBtnPositive = findViewById(R.id.withdrawal_positive);

        mToolbar = findViewById(R.id.withdrawal_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

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
        withdrawalService.getTest();
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
            case R.id.withdrawal_positive:
                showCustomToast("회원탈퇴가 완료되었습니다.");
                finish();
                break;
            case R.id.withdrawal_negative:
                showCustomToast("취소");
                finish();
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
