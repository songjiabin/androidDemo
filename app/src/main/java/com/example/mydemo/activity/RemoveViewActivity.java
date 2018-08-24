package com.example.mydemo.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydemo.R;
import com.example.mydemo.removeview.AnimatorView;
import com.example.mydemo.removeview.RemoveView_animator;
import com.example.mydemo.removeview.RemoveView_layout;
import com.example.mydemo.removeview.ScrollByView;
import com.example.mydemo.removeview.ScrollerView;
import com.example.mydemo.utils.ScreenUtils;

/**
 * author : 宋佳
 * time   : 2018/08/20
 * desc   :
 * version: 1.0.0
 */

public class RemoveViewActivity extends AppCompatActivity {

    private TextView tv_layout_method;
    private LinearLayout fl_remove_view;
    private TextView tv_animator_method;
    private TextView tv_animator_method_set_get;
    private TextView tv_animator_method_srcollto_scrollby;
    private TextView tv_animator_method_srcoll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_view);
        initView();
        initListener();
    }

    private void initView() {
        tv_layout_method = (TextView) findViewById(R.id.tv_layout_method);
        fl_remove_view = (LinearLayout) findViewById(R.id.fl_remove_view);
        tv_animator_method = (TextView) findViewById(R.id.tv_animator_method);
        tv_animator_method_set_get = (TextView) findViewById(R.id.tv_animator_method_set_get);
        tv_animator_method_srcollto_scrollby=(TextView)findViewById(R.id.tv_animator_method_srcollto_scrollby);
        tv_animator_method_srcoll=(TextView)findViewById(R.id.tv_animator_method_srcoll);
    }

    private void initListener() {
        //使用 layout方式进行 View的偏移
        tv_layout_method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_remove_view.removeAllViews();
                RemoveView_layout removeView_layout = new RemoveView_layout(RemoveViewActivity.this);
                removeView_layout.setBackgroundColor(Color.parseColor("#1296db"));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dp2px(RemoveViewActivity.this, 50f), ScreenUtils.dp2px(RemoveViewActivity.this, 50f));
                removeView_layout.setLayoutParams(params);
                fl_remove_view.addView(removeView_layout);
            }
        });

        //使用animator 来进行View的移动
        tv_animator_method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用补间动画
//                animatorMethod1();

                //使用属性动画
                animatorMethod2();

            }
        });


        //使用animator来进行伸缩 View
        tv_animator_method_set_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorMethod3();
            }
        });


        // 使用ScrollTo ScrollBy
        tv_animator_method_srcollto_scrollby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_remove_view.removeAllViews();
                ScrollByView removeView_animator = new ScrollByView(RemoveViewActivity.this);
                removeView_animator.setBackgroundColor(Color.parseColor("#1296db"));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dp2px(RemoveViewActivity.this, 50f), ScreenUtils.dp2px(RemoveViewActivity.this, 50f));
                removeView_animator.setLayoutParams(params);
                fl_remove_view.addView(removeView_animator);
            }
        });

        //使用 Scroller
        tv_animator_method_srcoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_remove_view.removeAllViews();
                ScrollerView removeView_animator = new ScrollerView(RemoveViewActivity.this);
                removeView_animator.setBackgroundColor(Color.parseColor("#1296db"));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dp2px(RemoveViewActivity.this, 50f), ScreenUtils.dp2px(RemoveViewActivity.this, 50f));
                removeView_animator.setLayoutParams(params);
                fl_remove_view.addView(removeView_animator);


                removeView_animator.smoothScrollTo(-400, -500);
            }
        });

    }


    //属性动画
    private void animatorMethod2() {

        fl_remove_view.removeAllViews();
        RemoveView_animator removeView_animator = new RemoveView_animator(RemoveViewActivity.this);
        removeView_animator.setBackgroundColor(Color.parseColor("#1296db"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dp2px(RemoveViewActivity.this, 50f), ScreenUtils.dp2px(RemoveViewActivity.this, 50f));
        removeView_animator.setLayoutParams(params);
        fl_remove_view.addView(removeView_animator);


        ObjectAnimator animation = ObjectAnimator.ofFloat(removeView_animator, "translationX", 300f, 100f, 900f);
        animation.setDuration(2000);
        animation.start();

        removeView_animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });


    }


    private void animatorMethod1() {
        fl_remove_view.removeAllViews();
        RemoveView_animator removeView_animator = new RemoveView_animator(RemoveViewActivity.this);
        removeView_animator.setBackgroundColor(Color.parseColor("#1296db"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dp2px(RemoveViewActivity.this, 50f), ScreenUtils.dp2px(RemoveViewActivity.this, 50f));
        removeView_animator.setLayoutParams(params);
        fl_remove_view.addView(removeView_animator);

//
        //次动画的移动是 补间动画
        Animation animation = AnimationUtils.loadAnimation(RemoveViewActivity.this, R.anim.translate_remove_view);
        removeView_animator.startAnimation(animation);


        //使用代码的格式
                /*

                TranslateAnimation animator_code = new TranslateAnimation(0, 300f, 0, 0);
                animator_code.setDuration( 2000);//设置动画持续时间
                animator_code.setFillAfter(true);//设置是否到指定的位置后不在移动

                removeView_animator.setAnimation(animator_code);

                */


        removeView_animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });
    }


    private void animatorMethod3() {
        fl_remove_view.removeAllViews();
        AnimatorView animatorView = new AnimatorView(RemoveViewActivity.this);
        animatorView.setBackgroundColor(Color.parseColor("#1296db"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dp2px(RemoveViewActivity.this, 50f), ScreenUtils.dp2px(RemoveViewActivity.this, 50f));
        animatorView.setLayoutParams(params);
        fl_remove_view.addView(animatorView);


        //使用属性动画 并自定义 get set进行动画
        // 这里我们设置2秒增加view的宽度300个像素
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animatorView, "widths", 300);
        objectAnimator.setDuration(2000);

        objectAnimator.start();

    }


    private void showToast() {
        Toast.makeText(RemoveViewActivity.this, "补间动画", Toast.LENGTH_SHORT).show();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 100f);
        valueAnimator.setTarget(tv_animator_method);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("animation", animation.getAnimatedValue() + "");
            }
        });


    }


}
