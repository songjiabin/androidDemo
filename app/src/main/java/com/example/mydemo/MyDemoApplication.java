package com.example.mydemo;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * author : 宋佳
 * time   : 2018/05/23
 * desc   :
 * version: 1.0.0
 */

public class MyDemoApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());

    }
}
