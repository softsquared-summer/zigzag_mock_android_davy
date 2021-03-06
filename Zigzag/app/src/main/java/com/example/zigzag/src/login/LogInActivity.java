package com.example.zigzag.src.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.example.zigzag.src.login.interfaces.LogInActivityView;
import com.example.zigzag.src.signIn.SignInActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.zigzag.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.zigzag.src.ApplicationClass.sSharedPreferences;

public class LogInActivity extends BaseActivity implements LogInActivityView {
    private EditText mEtId;
    private TextView mTvId;
    private EditText mEtPw;
    private TextView mTvPw;
    private TextView mTvFPw;
    private TextView mBtnLogin;


    private int count = 0;
    private Boolean isIdOk=false, isPwOk=false;

    private LoginService logInService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logInService = new LoginService(this);

        mEtId = findViewById(R.id.et_id);
        mTvId = findViewById(R.id.tv_login_id);
        mEtPw = findViewById(R.id.et_pw);
        mTvPw = findViewById(R.id.tv_login_pw);
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
                        if(!isEmail(mEtId.getText().toString())){
                            mTvId.setText("올바른 이메일 형식이 아닙니다");
                            mTvId.setTextColor(Color.RED);
                        }
                        else{
                            mTvId.setText("이메일");
                            mTvId.setTextColor(Color.BLACK);
             //               count++;
                        }
                    }
                }
        );

        mEtPw.addTextChangedListener(
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
                        if(mEtPw.getText().toString().length() < 6){
                            mTvPw.setText("비밀번호는 6자리 이상입니다");
                            mTvPw.setTextColor(Color.RED);
                        }
                        else{
                            mTvPw.setText("비밀번호");
                            mTvPw.setTextColor(Color.BLACK);
          ///                  count++;
                        }
                    }
                }
        );

        mBtnLogin = findViewById(R.id.tv_btn_login);
//        if(count > 1){
//            mBtnLogin.setClickable(true);
//            mBtnLogin.setBackground(this.getResources().getDrawable(R.drawable.border_round_pink));
//        }else{
//            mBtnLogin.setClickable(false);
//            mBtnLogin.setBackground(this.getResources().getDrawable(R.drawable.border_round_pink_white));
//        }

    }

    private void tryGetTest() {
        showProgressDialog();
        logInService.getTest();
    }

    private void tryPostLogIn(){
        showProgressDialog();
        //logInService.postLogIn("bige4739@gmail.com","1234");
        logInService.postLogIn(mEtId.getText().toString(),mEtPw.getText().toString());
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
    public void logInSuccess(boolean isSuccess, int code, String message ,String jwt) {
        hideProgressDialog();

        if(isSuccess) {
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN,jwt);
            editor.commit();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("id", mEtId.getText().toString());
            resultIntent.putExtra("jwt",X_ACCESS_TOKEN);
            setResult(1, resultIntent);
            finish();
            showCustomToast(mEtId.getText().toString() + "로그인성공");
        }else{
            showCustomToast("로그인 실패");
        }

//        Intent intent = new Intent(this, MypageFragment.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_login:
                tryPostLogIn();
                showCustomToast("로그인버튼");
                break;
            case R.id.tv_btn_signin:
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                showCustomToast("회원가입버튼");
                break;
            case R.id.iv_login_back:
                finish();
                showCustomToast("뒤로");
            default:
                break;
        }
    }
    public static boolean isEmail(String email){
        boolean returnValue = false;
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()){
            returnValue = true;
        }
        return returnValue;
    }
}
