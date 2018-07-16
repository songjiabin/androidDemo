package com.example.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rxjava.view.SwipeBackLayout;

/**
 * author : 宋佳
 * time   : 2018/07/16
 * desc   :
 * version: 1.0.0
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackLayout swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.bind();
    }
}
