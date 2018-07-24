package com.example.mydemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.fragment.ScrollableFragment;
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
    private RelativeLayout headerTitle;
    //动画
    TranslateAnimation mShowAction, mHiddenAction;
    private ScrollableFragment fragment;
    List<Fragment> fragmentList = new ArrayList<>();
    private ViewPagerAdapter adapterVP;
    private ViewPager vp;

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
        headerTitle = (RelativeLayout) findViewById(R.id.header_title);
        vp = (ViewPager) findViewById(R.id.vp);
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
        fragment = new ScrollableFragment();
        ScrollableFragment fragment1 = new ScrollableFragment();
        ScrollableFragment fragment2 = new ScrollableFragment();
        ScrollableFragment fragment3 = new ScrollableFragment();
        ScrollableFragment fragment4 = new ScrollableFragment();
        ScrollableFragment fragment5 = new ScrollableFragment();
        ScrollableFragment fragment6 = new ScrollableFragment();
        fragmentList.add(fragment);
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        fragmentList.add(fragment6);
        adapterVP = new ViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapterVP);
        tab.setupWithViewPager(vp);
    }
/*通过Fragmenbt的滚动距离回调
    * 注意：这里只能处理当ScrollLayout完全隐藏后，Fragment的ScrollView才能开始滚动事件，
    * 上滑的时候却优先执行ScrollLayout的方法
     */

    public void getonScroll(int i) {
        Log.e("activity======I:", "i:" + i);
//        srl.setEnabled(i<450);

        if (i > 700) {
            headerTitle.setAnimation(mHiddenAction);
            headerTitle.setVisibility(View.GONE);
        }
        if (i < 450) {
            headerTitle.setAnimation(mShowAction);
            headerTitle.setVisibility(View.VISIBLE);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
