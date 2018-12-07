package com.example.mydemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.mydemo.bean.MyObjectBox;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * author : 宋佳
 * time   : 2018/05/23
 * desc   :
 * version: 1.0.0
 */

public class MyDemoApplication extends Application {


    private static Context context;
    private static BoxStore mBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        context = getApplicationContext();
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
        if (BuildConfig.DEBUG) {
            // 添加调试
            boolean start=  new AndroidObjectBrowser(mBoxStore).start(this);
            Log.e("====start=======",start+"");
        }


    }


    //返回
    public static Context getContextObject() {
        return context;
    }

    public static BoxStore getBoxStore() {
        return mBoxStore;
    }
}
