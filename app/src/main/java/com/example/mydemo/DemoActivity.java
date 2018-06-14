package com.example.mydemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class DemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_value_animator);


        Button btn = (Button) findViewById(R.id.btn);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);

        //透明
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 1f, 0f, 1f);
        //操作button的透明度
        objectAnimator.setDuration(3000);
        objectAnimator.start();

        //旋转
        // 动画效果是:0 - 360
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(btn2, "rotation", 0f, 360f);
        objectAnimator2.setDuration(5000);
        objectAnimator2.start();


        //平移
        float curTranslationX = btn3.getX();
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(btn3, "translationX", curTranslationX, curTranslationX + 300, curTranslationX);
        objectAnimator3.setDuration(5000);
        objectAnimator3.start();

        //缩放
        ObjectAnimator animator = ObjectAnimator.ofFloat(btn4, "scaleX", 1f, 3f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴缩放
        // 动画效果是:放大到3倍,再缩小到初始大小
        animator.setDuration(5000);
        animator.start();


    }

}
