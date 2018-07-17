package com.example.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.example.mydemo.view.MarqueeView;
import com.example.searchview.SearchView;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class DemoActivity extends AppCompatActivity {


    private SearchView searchView;
    private WebView mWebView;
    private MarqueeView marqueeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_marquee);

        marqueeView = (MarqueeView) findViewById(R.id.marqueeView);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始
                marqueeView.startSrcoller();
            }
        });

        findViewById(R.id.reStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新开始
                marqueeView.reStartScroll();
            }
        });

        findViewById(R.id.paruse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂停
                marqueeView.pauseScroll();
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止
                marqueeView.stopScroll();
            }
        });


    }


}
