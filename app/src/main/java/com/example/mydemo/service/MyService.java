package com.example.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * author : 宋佳
 * time   : 2018/10/24
 * desc   :
 * version: 1.0.0
 */

public class MyService extends Service {

    private String data;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


    public class MyBinder extends Binder {
        public void setData(String data) {
            MyService.this.data = data;
            Log.i("》》》", data);
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }
}
