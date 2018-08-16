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

public class ViewGroupB extends LinearLayout {


    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("》》》", " ViewGroupB dispatchTouchEvent  ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("》》》", " ViewGroupB onInterceptTouchEvent  ");
        //将事件拦截   ViewC 不能接收到事件
        return true;
    }


    private int lastX;
    private int lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("》》》", " ViewC onTouchEvent  ");

        int x = (int) event.getX();
        int y = (int) event.getY();

//        Log.i("《《《", "x==" + x + "y==" + y);


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录下当你按下的位置
                lastX = x;
                lastY = y;

                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                //移动的距离
                int offSetX = x - lastX;
                int offSety = y - lastY;


                //从起点到不停的滑动中 移动的距离
                int l = getLeft() + offSetX;
                int b = getBottom() + offSety;
                int r = getRight() + offSetX;
                int t = getTop() + offSety;


                //重新确认位置
                layout(l, t, r, b);


                break;
            case MotionEvent.ACTION_UP:
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
                params.setMargins(getLeft(), getTop(), 0, 0);

                break;
            default:
                break;
        }


        return true;

    }



}
