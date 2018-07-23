package com.example.mydemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/07/23
 * desc   : 类似微博的 主页的 界面
 * version: 1.0.0
 */

public class WeiboActivity extends AppCompatActivity {


    private Banner header;
    List<Object> img = new ArrayList<>();
    String url = "http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png";
    private String[] titles = new String[]{"最新", "专栏", "官方", "活动", "攻略", "娱乐", "收藏"};
    private TabLayout tab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo);
        initView();
        initBanner();
        initTabLayout();
        initFragment();
    }


    private void initView() {
        header = (Banner) findViewById(R.id.header);
        tab = (TabLayout) findViewById(R.id.tab);
    }


    //轮播图
    private void initBanner() {
        //圆形指示器
        header.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //指示器居中
        header.setIndicatorGravity(BannerConfig.CENTER);
        img.add("http://img.zcool.cn/community/01ca8c573c04b832f8757cb97b2444.jpg@1280w_1l_2o_100sh.jpg");
        img.add("http://img.zcool.cn/community/011a5859ac137ea8012028a92fc78a.jpg@1280w_1l_2o_100sh.jpg");
        img.add("http://img.zcool.cn/community/01e6e15a5b0570a80120121fa45554.gif");
        header.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context)
                        .load(o)
                        .into(imageView);
            }
        });
        header.setImages(img);
        header.start();
    }


    /*初始化tab标签*/
    private void initTabLayout() {
        for (int i = 0; i < titles.length; i++) {
            tab.addTab(tab.newTab().setText(titles[i]));
        }
    }


    private void initFragment() {

    }


}
