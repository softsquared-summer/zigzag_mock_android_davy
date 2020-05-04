package com.example.zigzag.src.home.mypage;

import android.content.Intent;
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
import com.example.zigzag.src.login.LogInActivity;
import com.example.zigzag.src.setup.SetupActivity;

public class MypageFragment extends Fragment {
    ViewGroup viewGroup;
    LinearLayout btn_login;
    TextView tv_hello, tv_id;
    ImageView btn_setup;
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

//        Bundle extra = this.getArguments();
//        if(extra != null){
//            extra = getArguments();
//            String id = extra.getString("id");
//            tv_hello.setText("회원님 안녕하세요!");
//            tv_id.setText(id);
//        }

        return viewGroup;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == 1){
                String id = (String) data.getExtras().get("id");
                tv_hello.setText("회원님 안녕하세요!");
                tv_id.setText(id);
                btn_login.setClickable(false);
            }
        }
    }
}
