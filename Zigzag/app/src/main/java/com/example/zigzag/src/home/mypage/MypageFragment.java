package com.example.zigzag.src.home.mypage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zigzag.R;
import com.example.zigzag.src.bucket.BucketActivity;
import com.example.zigzag.src.login.LogInActivity;
import com.example.zigzag.src.setup.SetupActivity;

import static com.example.zigzag.src.ApplicationClass.sSharedPreferences;

public class MypageFragment extends Fragment {
    ViewGroup viewGroup;
    private LinearLayout btn_login;
    private TextView tv_hello, tv_id;
    private ImageView btn_setup;
    private String mId, mJwt;
    private ImageView mBtnBucket;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_mypage,container,false);

        btn_login = (LinearLayout)viewGroup.findViewById(R.id.linear_login);
        btn_setup = viewGroup.findViewById(R.id.iv_login_set);
        tv_hello = viewGroup.findViewById(R.id.tv_my_hello);
        tv_id = viewGroup.findViewById(R.id.tv_my_id);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivityForResult(intent,1);
            }
        });
        btn_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetupActivity.class);
                startActivity(intent);
            }
        });

        mBtnBucket = viewGroup.findViewById(R.id.mypage_bucket);
        mBtnBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BucketActivity.class);
                startActivity(intent);
            }
        });


        if(mJwt != null){
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString("id", mId);
            editor.apply();
            editor.commit();
            tv_hello.setText("회원님 안녕하세요!");
            tv_id.setText(sSharedPreferences.getString("id", mId));
        }else{
            tv_hello.setText("지그재그 로그인 및 회원가입 >");
            tv_id.setText("지그재그 ID로 한 번에 결제하세요.");
            tv_hello.setTextColor(getResources().getColor(R.color.colorSelectedDot));
            tv_hello.setTextSize(15f);
            tv_id.setTextSize(10f);
        }
        return viewGroup;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == 1){
                mId = (String) data.getExtras().get("id");
                mJwt = (String) data.getExtras().get("jwt");
                tv_hello.setText("회원님 안녕하세요!");
                tv_id.setText(sSharedPreferences.getString("id", mId));
                btn_login.setClickable(false);
                btn_setup.setClickable(true);
            }
        }else{
            btn_setup.setClickable(false);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("id", mId);
        editor.putString("jwt",mJwt);
        editor.apply();
        editor.commit();
    }
}
