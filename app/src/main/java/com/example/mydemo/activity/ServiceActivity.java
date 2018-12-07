package com.example.mydemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mydemo.R;
import com.example.mydemo.service.MyService;

/**
 * author : 宋佳
 * time   : 2018/10/24
 * desc   :
 * version: 1.0.0
 */

public class ServiceActivity extends AppCompatActivity implements ServiceConnection {


    private MyService.MyBinder myBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        final Intent intent = new Intent(ServiceActivity.this, MyService.class);
        bindService(intent, ServiceActivity.this, Context.BIND_AUTO_CREATE);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始服务
                ServiceActivity.this.startService(intent);
                myBinder.setData("数据是这个");
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止服务
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        myBinder = (MyService.MyBinder) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
