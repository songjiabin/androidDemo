package com.example.mydemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/10/10
 * desc   :
 * version: 1.0.0
 */

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
}
