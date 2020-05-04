package com.example.zigzag.src;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.zigzag.R;

public class CustomDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private Button mBtnNegative, mBtnPositive;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;

        WindowManager.LayoutParams IpWindow = new WindowManager.LayoutParams();
        IpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        IpWindow.dimAmount = 0.8f;
        IpWindow.width = WindowManager.LayoutParams.MATCH_PARENT;
        IpWindow.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(IpWindow);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_alert);

        mBtnNegative = findViewById(R.id.negativeButton);
        mBtnPositive = findViewById(R.id.positiveButton);

        mBtnPositive.setOnClickListener(this);
        mBtnNegative.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.negativeButton:
                dismiss();
                break;
            case R.id.positiveButton:

        }
    }
}
