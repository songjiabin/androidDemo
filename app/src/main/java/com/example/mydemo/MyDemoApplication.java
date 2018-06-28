package com.example.mydemo;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * author : 宋佳
 * time   : 2018/05/23
 * desc   :
 * version: 1.0.0
 */

public class MyDemoApplication extends Application {


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        context = getApplicationContext();

    }


    //返回
    public static Context getContextObject(){
        return context;
    }
}
