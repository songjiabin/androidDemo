package com.example.mydemo.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.mydemo.R;
import com.example.mydemo.bean.PopularWindowBean;
import com.example.mydemo.utils.CommonPopularWindow;
import com.example.mydemo.utils.CustomerPopular;
import com.example.mydemo.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/08/14
 * desc   : popular  自定义
 * version: 1.0.0
 */

public class PopularWindowActivity extends AppCompatActivity {


    private Button btn_nomar_popular_bottom;

    private View popupView;
    private PopupWindow popupWindow;
    private Button btn_nomar_popular_left;
    private Button btn_nomar_popular_right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_window);
        btn_nomar_popular_bottom = (Button) findViewById(R.id.btn_nomar_popular_bottom);
        btn_nomar_popular_left = (Button) findViewById(R.id.btn_nomar_popular_left);

        btn_nomar_popular_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //向下弹框
                showPopularWindow();
                bottom(v);

            }
        });

        btn_nomar_popular_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //向左弹框
                showPopularWindow();
                left(v);
            }
        });

        findViewById(R.id.btn_nomar_popular_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopularWindow();
                top(v);
            }
        });
        btn_nomar_popular_right = (Button) findViewById(R.id.btn_nomar_popular_right);
        findViewById(R.id.btn_nomar_popular_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopularWindow();
                right(v);
            }
        });


        findViewById(R.id.btn_popular_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonPopularWindow popu = new CommonPopularWindow.Builder(PopularWindowActivity.this)
                        .setView(R.layout.popular_window)
                        .setWidthAndHeigth(0, 0)
                        .setBackGroundLevel(0.5f).setViewOnclickListener(new CommonPopularWindow.ViewInterface() {
                            @Override
                            public void getChildView(View view, int layoutResId) {
                                //点击的具体idView
                                view.findViewById(R.id.tv_popuWindow1).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Log.i("数据弹框", "弹框了");
                                    }
                                });
                            }
                        }).create();
                popu.showAsDropDown(v);
            }
        });


        findViewById(R.id.btn_popular_all2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                final List<PopularWindowBean> popularWindowBeanList = new ArrayList<>();
                for (int i = 0; i < 60; i++) {
                    PopularWindowBean popularWindowBean = new PopularWindowBean();
                    popularWindowBean.setData(i + "");
                    popularWindowBeanList.add(popularWindowBean);
                }
                new CustomerPopular(PopularWindowActivity.this).
                        refreshListDatas(popularWindowBeanList, (RelativeLayout) findViewById(R.id.ll_parent), ListView.CHOICE_MODE_MULTIPLE, null).setPopularItemOnclick(new CustomerPopular.OnProductPopularItemOnclick() {
                    @Override
                    public void getOnItemPosition(int[] positionArray) {
                        //点击的是哪个？
                        for (int i = 0; i < positionArray.length; i++) {
                            Log.i("数据是", popularWindowBeanList.get(positionArray[i]).getData());

                        }
                    }
                });

            }
        });


    }


    //展示平常的 popular
    private void showPopularWindow() {

        if (popupView != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            return;
        }


        //准备PopupWindow的布局View
        popupView = LayoutInflater.from(this).inflate(R.layout.popular_window, null);
        //初始化一个PopupWindow，width和height都是WRAP_CONTENT


        popupView.measure(0, 0);

        popupWindow = new PopupWindow(popupView, popupView.getMeasuredWidth(), popupView.getMeasuredHeight(), true);


//  不要这些创建 否则不能得到 popularWindow 的高度和宽度
//        popupWindow = new PopupWindow(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        //设置PopupWindow的视图内容
        popupWindow.setContentView(popupView);
        //点击空白区域PopupWindow消失，这里必须先设置setBackgroundDrawable，否则点击无反应
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        //设置PopupWindow动画
        popupWindow.setAnimationStyle(R.style.Theme_AppCompat_DialogWhenLarge);
        //设置是否允许PopupWindow的范围超过屏幕范围
        popupWindow.setClippingEnabled(true);
        //注意必须设置焦点   不然 isShowing不执行
        popupWindow.setFocusable(true);
        //设置PopupWindow消失监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });


    }

    //向下弹框
    private void bottom(View view) {
        //showAsDropDown(View anchor) : 显示在参照物的周围
        //showAsDropDown(View anchor, int xoff, int yoff)   xoff、yoff分别是X轴、Y轴的偏移量
        //如果不设置  xoff、yoff  默认显示到 参照物的下面
        //不设置 xoff ,yoff 默认到 参照物下面


        //public void showAtLocation(View parent, int gravity, int x, int y)
        //设置在父控件的位置  example: Gravity.BOTTOM 表示在父控件的底部弹出   x ， y  分别是 x y 轴的偏移量


        popupWindow.showAsDropDown(view);


    }


    //向左弹框
    private void left(View view) {
        //public void showAtLocation(View parent, int gravity, int x, int y)
        //设置在父控件的位置  example: Gravity.BOTTOM 表示在父控件的底部弹出   x ， y  分别是 x y 轴的偏移量


        //得到点击的控件的坐标
        int[] positions = new int[2];
        view.getLocationOnScreen(positions);
        //其实 positions[0] 表示的横坐标  position[1]表示的事纵坐标

        int b = popupWindow.getWidth();


        //整个的content的位置  findViewById(android.R.id.content)
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0] - popupWindow.getWidth(), positions[1]);
    }


    //向上弹框
    private void top(View view) {
        //得到点击的控件的坐标
        int[] positions = new int[2];
        view.getLocationOnScreen(positions);
        //其实 positions[0] 表示的横坐标  position[1]表示的事纵坐标
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, positions[0], positions[1] - popupWindow.getHeight());
    }

    //向右弹框
    private void right(View view) {
        //得到点击的控件的坐标
        int[] positions = new int[2];
        view.getLocationOnScreen(positions);

        //得到屏幕高度
        int screenHeight = ScreenUtils.getScreenHeight(this);

        int y = screenHeight - popupWindow.getHeight();

        //其实 positions[0] 表示的横坐标  position[1]表示的事纵坐标
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, positions[0] + btn_nomar_popular_right.getMeasuredWidth(), y);
    }


}
