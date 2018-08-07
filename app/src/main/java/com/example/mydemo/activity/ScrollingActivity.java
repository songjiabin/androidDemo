package com.example.mydemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/08/07
 * desc   :
 * 原文地址： https://www.jianshu.com/p/6d139d0fc46a
 * version: 1.0.0
 */

public class ScrollingActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


    private AppBarLayout appBar;
    private View toolbarOpen;
    private View toolbarClose;
    private View bgToolbarOpen;
    private View bgToolbarClose;
    private View bgContent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);


        appBar = (AppBarLayout) findViewById(R.id.app_bar);

        appBar.addOnOffsetChangedListener(this);
        toolbarOpen = findViewById(R.id.include_toolbar_open);
        toolbarClose = findViewById(R.id.include_toolbar_close);


        bgToolbarOpen = findViewById(R.id.bg_toolbar_open);
        bgToolbarClose = findViewById(R.id.bg_toolbar_close);


        bgContent = findViewById(R.id.bg_content);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //垂直方向偏移量
        int offset = Math.abs(verticalOffset);
        //最大偏移距离
        int scrollRange = appBarLayout.getTotalScrollRange();


        Log.i("宋佳宾_offset", offset + "");

        Log.i("宋佳宾_scrollRange", scrollRange + "");


        if (offset <= scrollRange / 2) {//当滑动没超过一半，展开状态下toolbar显示内容，根据收缩位置，改变透明值
            Log.i("数据","offset <= scrollRange / 2");
            toolbarOpen.setVisibility(View.VISIBLE);
            toolbarClose.setVisibility(View.GONE);
            //根据偏移百分比 计算透明值
            float scale2 = (float) offset / (scrollRange / 2);
            int alpha2 = (int) (255 * scale2);
            bgToolbarOpen.setBackgroundColor(Color.argb(alpha2, 25, 131, 209));
        } else {//当滑动超过一半，收缩状态下toolbar显示内容，根据收缩位置，改变透明值
            Log.i("数据","offset > scrollRange / 2");
            toolbarClose.setVisibility(View.VISIBLE);
            toolbarOpen.setVisibility(View.GONE);
            float scale3 = (float) (scrollRange - offset) / (scrollRange / 2);
            int alpha3 = (int) (255 * scale3);
            bgToolbarClose.setBackgroundColor(Color.argb(alpha3, 25, 131, 209));
        }
        //根据偏移百分比计算扫一扫布局的透明度值
        float scale = (float) offset / scrollRange;
        int alpha = (int) (255 * scale);
        bgContent.setBackgroundColor(Color.argb(alpha, 25, 131, 209));


    }
}
