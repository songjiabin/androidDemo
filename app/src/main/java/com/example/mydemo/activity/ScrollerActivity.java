package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mydemo.R;
import com.example.mydemo.view.MyScrollerView;

/**
 * author : 宋佳
 * time   : 2018/08/24
 * desc   :
 * version: 1.0.0
 */

public class ScrollerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);


        MyScrollerView myScrollerView = (MyScrollerView) findViewById(R.id.MyScrollerView);
        myScrollerView.beginScroll();
    }
}
