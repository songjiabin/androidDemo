package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mydemo.R;
import com.example.mydemo.utils.AppUtils;

/**
 * author : 宋佳
 * time   : 2018/10/12
 * desc   :
 * version: 1.0.0
 */

public class GetPhoneNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        Toast.makeText(this, AppUtils.getPhoneNumber(this),  Toast.LENGTH_SHORT).show();
    }
}
