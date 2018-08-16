package com.example.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * author : 宋佳
 * time   : 2018/08/10
 * desc   :
 * version: 1.0.0
 */

public class ViewGroupA extends LinearLayout {


    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("》》》", " ViewGroupA dispatchTouchEvent  ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("》》》", " ViewGroupA onInterceptTouchEvent  ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("》》》", " ViewGroupA onTouchEvent  ");
        return super.onTouchEvent(event);
    }


}
