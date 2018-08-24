package com.example.mydemo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.fragment.WeiBoTabLeftFragment;
import com.example.mydemo.view.WeiBoScrollView;

/**
 * author : 宋佳
 * time   : 2018/08/16
 * desc   :
 * version: 1.0.0
 */

public class WeiboActivity4 extends AppCompatActivity {


    private WeiBoScrollView scro;
    private TextView tv_title_text;
    private View ll_sus_tab;
    private View ll_tab;
    private ImageView iv_img;
    private View title_divider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo4);

        scro = (WeiBoScrollView) findViewById(R.id.scro);
        tv_title_text = (TextView) findViewById(R.id.tv_title_text);
        ll_sus_tab = (View) findViewById(R.id.ll_sus_tab);
        ll_tab = (View) findViewById(R.id.ll_tab);
        iv_img = (ImageView) findViewById(R.id.iv_img);

        title_divider = (View) findViewById(R.id.title_divider);

        iv_img.setFocusable(true);
        iv_img.setFocusableInTouchMode(true);
        iv_img.requestFocus();


        //添加默认显示的Fragment
        initFragment();


        scro.setOnScrollViewListener(new WeiBoScrollView.OnScrollViewListener() {
            @Override
            public void onSrcollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //当滑动的时候
                //如果向上滑动的距离 >= iv_img.height - tv_title_text.height,隐藏的标题导航栏设置显示
                int imgHeight = iv_img.getHeight();
                int titleHeight = tv_title_text.getHeight();


                int distanceScrollY = imgHeight - titleHeight;

                if (scrollY >= distanceScrollY) {
                    //需要显示 固定的标题栏啦
                    ll_sus_tab.setVisibility(View.VISIBLE);
                    title_divider.setVisibility(View.VISIBLE);
                } else {
                    ll_sus_tab.setVisibility(View.INVISIBLE);
                    title_divider.setVisibility(View.GONE);
                }

                //设置标题栏渐变
                if (scrollY <= 0) {
                    //初始位置：未滑动时，设置标题背景透明
                    tv_title_text.setBackgroundColor(Color.TRANSPARENT);
                    tv_title_text.setTextColor(Color.WHITE);
                } else if (scrollY > 0 && scrollY <= distanceScrollY) {
                    float scale = Float.valueOf(scrollY) / distanceScrollY;
                    float alpha = 255 * scale;

                    int al = (int) alpha;
                    tv_title_text.setBackgroundColor(Color.argb(al, 255, 255, 255));
                    tv_title_text.setTextColor(Color.argb(al, 0, 0, 0));
                } else {
                    tv_title_text.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    tv_title_text.setTextColor(Color.argb(255, 0, 0, 0));
                }







            }
        });

    }

    private void initFragment() {


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_container, WeiBoTabLeftFragment.newInstance(R.layout.fragment_weibo_left));
        fragmentTransaction.commit();

    }


}
