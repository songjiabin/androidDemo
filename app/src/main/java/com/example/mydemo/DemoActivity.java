package com.example.mydemo;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mydemo.utils.ScreenUtils;
import com.example.mydemo.view.MyScroller2;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Animator mAnimator;
    private TextView right;
    private TextView left;
    private MyScroller2 myScroller2;
    private int moveX = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_my_scroller2);

        left = (TextView) findViewById(R.id.left);
        right = (TextView) findViewById(R.id.right);
        myScroller2 = (MyScroller2) findViewById(R.id.myScroller2);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (moveX < 0) {
            moveX = 0;
        }
        if (moveX >= ScreenUtils.getScreenWidth(DemoActivity.this) * 3) {
            moveX = ScreenUtils.getScreenWidth(DemoActivity.this) * 3;
        }
        switch (v.getId()) {
            case R.id.right:
                moveX += ScreenUtils.getScreenWidth(DemoActivity.this);
                myScroller2.scrollTo(moveX, 0);
                break;
            case R.id.left:
                moveX -= ScreenUtils.getScreenWidth(DemoActivity.this);
                myScroller2.scrollTo(moveX, 0);
                break;
            default:
                break;
        }
    }
}
