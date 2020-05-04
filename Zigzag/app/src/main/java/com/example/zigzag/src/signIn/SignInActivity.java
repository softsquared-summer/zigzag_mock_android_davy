package com.example.zigzag.src.signIn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.signIn.interfaces.SignInActivityView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends BaseActivity implements SignInActivityView, CompoundButton.OnCheckedChangeListener {
    private EditText mEtSignId,mEtSignPw,mEtSignPh;
    private TextView mTvSignId,mTvSignPw,mTvSignPh;

    private SignInService signInService;

    private CheckBox mCheckAll, mCheck14, mCheckService, mCheckPrivacy, mCheckAlarm;

    private boolean isAll, is14, isSv, isP, isA;

    private String id,pw,ph,sIs14,sIsSv,sIsP,sIsA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signInService = new SignInService(this);

        mEtSignId = findViewById(R.id.et_sign_id);
        mEtSignPw = findViewById(R.id.et_sign_pw);
        mEtSignPh = findViewById(R.id.et_sign_ph);
        mTvSignId = findViewById(R.id.tv_signIn_id);
        mTvSignPw = findViewById(R.id.tv_sign_pw);
        mTvSignPh = findViewById(R.id.tv_sign_phone);

        id = mEtSignId.getText().toString();
        pw = mEtSignPw.getText().toString();
        ph = mEtSignPh.getText().toString();

        mCheckAll = findViewById(R.id.checkbox_all);
        mCheck14 = findViewById(R.id.checkbox_over_14);
        mCheckService = findViewById(R.id.checkbox_service_agree);
        mCheckPrivacy = findViewById(R.id.checkbox_privacy_agree);
        mCheckAlarm = findViewById(R.id.checkbox_alarm_agree);

        mCheckAll.setOnCheckedChangeListener(this);
        mCheck14.setOnCheckedChangeListener(this);
        mCheckService.setOnCheckedChangeListener(this);
        mCheckPrivacy.setOnCheckedChangeListener(this);
        mCheckAlarm.setOnCheckedChangeListener(this);

        if(is14 == true){
            sIs14 = "Y";
        }
        if(isSv == true){
            sIsSv = "Y";
        }
        if(isP == true){
            sIsP = "Y";
        }
        if(isA == true){
            sIsA = "Y";
        }

        mEtSignId.addTextChangedListener(
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
                        if(!isEmail(mEtSignId.getText().toString())){
                            mTvSignId.setText("올바른 이메일 형식이 아닙니다");
                            mTvSignId.setTextColor(R.color.colorError);
                        }
                        else{
                            mTvSignId.setText("이메일");
                        }
                    }
                }
        );

        mEtSignPw.addTextChangedListener(
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
                        if(mEtSignPw.getText().toString().length() < 8){
                            mTvSignPw.setText("8자리 이상 입력해주세요.");
                            mTvSignPw.setTextColor(R.color.colorError);
                        }
                        else if(mEtSignPw.getText().toString() == null){
                            mTvSignPw.setText("비밀번호를 입력해주세요.");
                            mTvSignPw.setTextColor(R.color.colorError);
                        }
                        else if(!isPw(mEtSignPw.getText().toString())){
                            mTvSignPw.setText("영문,숫자,특수문자를 모두 포함해주세요.");
                            mTvSignPw.setTextColor(R.color.colorError);
                        }
                        else{
                            mTvSignPw.setText("비밀번호");
                        }
                    }
                }
        );

        mEtSignPh.addTextChangedListener(
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
                        if(mEtSignPh.getText().toString().length()<10){
                            mTvSignPh.setText("휴대폰번호는 10자리 이상입니다.");
                            mTvSignPh.setTextColor(R.color.colorError);
                        }
                        else{
                            mTvSignPh.setText("휴대폰번호");
                        }
                    }
                }
        );



    }

    private void tryGetTest() {
        showProgressDialog();
        signInService.getTest();
    }

    private void tryPostSignIn(){
        showProgressDialog();
        id = mEtSignId.getText().toString();
        pw = mEtSignPw.getText().toString();
        ph = mEtSignPh.getText().toString();
        if(is14 == true){
            sIs14 = "Y";
        }else{
            sIs14 = "N";
        }
        if(isSv == true){
            sIsSv = "Y";
        }else{
            sIsSv = "N";
        }
        if(isP == true){
            sIsP = "Y";
        }else{
            sIsP = "N";
        }
        if(isA == true){
            sIsA = "Y";
        }else{
            sIsA = "N";
        }
        signInService.postSignIn(id,pw,ph,sIs14,sIsSv,sIsP,sIsA);
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
    public void SignInSuccess(boolean isSuccess, int code, String message) {
        hideProgressDialog();
        if(isSuccess == true) {
            showCustomToast("회원가입되셨습니다!");
//        Intent intent = new Intent(this, LogInActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
            finish();
        }else{
            showCustomToast("항목을 다시 확인하세요");
        }
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_sign_back:
                showCustomToast("뒤로");
                finish();
                break;
            case R.id.tv_btn_signin:
                tryPostSignIn();
                showCustomToast("회원가입");
                break;
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

    public static boolean isPw(String pw){
        boolean returnValue = false;
        String regex = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%^*#?&]).{8,20}.$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pw);
        if(m.matches()){
            returnValue = true;
        }
        return returnValue;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.checkbox_all:
                isAll = isChecked;
                if(isChecked){
                    if(!mCheck14.isChecked())
                        mCheck14.setChecked(isChecked);
                    if(!mCheckService.isChecked())
                        mCheckService.setChecked(isChecked);
                    if(!mCheckPrivacy.isChecked())
                        mCheckPrivacy.setChecked(isChecked);
                    if(!mCheckAlarm.isChecked())
                        mCheckAlarm.setChecked(isChecked);
                }else{
                    if(mCheckAll.isPressed()){
                        if(!isAll) {
                            mCheck14.setChecked(isChecked);
                            mCheckService.setChecked(isChecked);
                            mCheckPrivacy.setChecked(isChecked);
                            mCheckAlarm.setChecked(isChecked);
                        }else if(isAll){
                            mCheck14.setChecked(false);
                            mCheckService.setChecked(false);
                            mCheckPrivacy.setChecked(false);
                            mCheckAlarm.setChecked(false);
                        }
                    }
                }
            case R.id.checkbox_over_14:
                is14 = isChecked;
                if(is14&&isP&&isSv&&isA){
                    mCheckAll.setChecked(true);
                }else {
                    mCheckAll.setChecked(false);
                }
                break;
            case R.id.checkbox_service_agree:
                isSv = isChecked;
                if(is14&&isP&&isSv&&isA){
                    mCheckAll.setChecked(true);
                }else {
                    mCheckAll.setChecked(false);
                }
                break;
            case R.id.checkbox_privacy_agree:
                isP = isChecked;
                if(is14&&isP&&isSv&&isA){
                    mCheckAll.setChecked(true);
                }else {
                    mCheckAll.setChecked(false);
                }
                break;
            case R.id.checkbox_alarm_agree:
                isA = isChecked;
                if(is14&&isP&&isSv&&isA){
                    mCheckAll.setChecked(true);
                }else {
                    mCheckAll.setChecked(false);
                }
                break;
        }
    }
}
