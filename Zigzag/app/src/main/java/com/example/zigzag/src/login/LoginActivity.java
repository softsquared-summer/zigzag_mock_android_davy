package com.example.zigzag.src.login;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.login.interfaces.LoginActivityView;

public class LoginActivity extends BaseActivity implements LoginActivityView {
    private TextView mTvHelloWorld;
    private EditText mEtId;
    private TextView mTvFPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTvHelloWorld = findViewById(R.id.main_tv_hello_world);
        mEtId = findViewById(R.id.et_id);
        mTvFPw = findViewById(R.id.tv_fpw);

        mTvFPw.setPaintFlags(mTvFPw.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mEtId.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void afterTextChanged(Editable s) {
                        if(mEtId.getText().toString().length() < 5){
//                            mEtId.setTextColor(R.color.colorAccent);

                            showCustomToast("aaa");
                        }
                        else{
//                            mEtId.setTextColor(R.color.colorSplashBackground);
                        }
                    }
                }
        );
    }

    private void tryGetTest() {
        showProgressDialog();

        final LoginService mainService = new LoginService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_hello_world:
                tryGetTest();
                break;
            default:
                break;
        }
    }
}
