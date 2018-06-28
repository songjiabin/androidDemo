package com.example.mydemo.js;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.mydemo.MyDemoApplication;

/**
 * author : 宋佳
 * time   : 2018/06/25
 * desc   :
 * version: 1.0.0
 */

public class AndroidtoJs extends Object {


    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        System.out.println("JS调用了Android的hello方法");

        Toast.makeText(MyDemoApplication.getContextObject(),"JS调用了Android的hello方法",Toast.LENGTH_SHORT).show();

    }


}
