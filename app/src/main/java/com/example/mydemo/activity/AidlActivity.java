package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mydemo.IMyAidlInterface;
import com.example.mydemo.R;
import com.example.mydemo.thread.AsyncTaskDemo;

/**
 * author : 宋佳
 * time   : 2018/10/25
 * desc   :
 * version: 1.0.0
 */

public class AidlActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        AsyncTaskDemo asyncTaskDemo=new AsyncTaskDemo(this);
        asyncTaskDemo.execute();
    }


}
