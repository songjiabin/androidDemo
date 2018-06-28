package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.mydemo.R;
import com.example.mydemo.js.AndroidtoJs;

/**
 * author : 宋佳
 * time   : 2018/06/25
 * desc   :
 * version: 1.0.0
 */

public class JsToAndroidFragment2 extends Fragment {


    private WebView mWebView;

    public static JsToAndroidFragment2 newInstance(@LayoutRes int sampleLayoutRes) {
        JsToAndroidFragment2 fragment = new JsToAndroidFragment2();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_android_js2, container, false);



        mWebView = (WebView) view.findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        mWebView.addJavascriptInterface(new AndroidtoJs(), "test");//AndroidtoJS类对象映射到js的test对象

        // 加载JS代码
    // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/javascript2.html");



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

    }


}
