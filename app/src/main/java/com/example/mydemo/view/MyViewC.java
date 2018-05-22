package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class MyViewC extends View {


    public MyViewC(Context context) {
        super(context);
    }

    public MyViewC(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewC(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("sjb", "dispatchTouchEvent事件----C" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("sjb", "onTouchEvent----C" + ev.getAction());
        return super.onTouchEvent(ev);
    }
}
