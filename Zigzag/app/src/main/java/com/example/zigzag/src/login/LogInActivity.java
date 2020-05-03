package com.example.zigzag.src.login;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.zigzag.src.home.mypage.MypageFragment;
import com.example.zigzag.src.login.interfaces.LogInActivityView;
import com.example.zigzag.src.login.models.LogInResponse;
import com.example.zigzag.src.signIn.SignInActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInActivity extends BaseActivity implements LogInActivityView {
    private EditText mEtId;
    private TextView mTvId;
    private EditText mEtPw;
    private TextView mTvPw;
    private TextView mTvFPw;

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
                            mTvId.setTextColor(R.color.colorError);
                        }
                        else{
                            mTvId.setText("이메일");
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
                            mTvPw.setTextColor(R.color.colorError);
                        }
                        else{
                            mTvPw.setText("비밀번호");
                        }
                    }
                }
        );


    }

    private void tryGetTest() {
        showProgressDialog();
        logInService.getTest();
    }

    private void tryPostLogIn(){
        showProgressDialog();
        logInService.postLogIn("bige4739@gmail.com","1234");
        //logInService.postLogIn(mEtId.getText().toString(),mEtPw.getText().toString());
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
    public void logInSuccess(LogInResponse.LogInResult logInResult) {
        hideProgressDialog();
        MypageFragment fragment = new MypageFragment();
        Bundle bundle = new Bundle();
        String contents = mEtId.getText().toString();
        bundle.putString("id",contents);
        fragment.setArguments(bundle);
        showCustomToast(contents+"로그인성공");
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
