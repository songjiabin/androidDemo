package com.example.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * time   : 2018/05/25
 * desc   : 跟随手势 滑动的 View
 * version: 1.0.0
 */

public class MyMoveView extends View {
    private int lastX;
    private int lastY;

    public MyMoveView(Context context) {
        super(context);
        initView(context);
    }

    public MyMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                //抬起
                break;
            case MotionEvent.ACTION_MOVE:
                //当移动的时候
                int offetX = x - lastX;
                int offetY = y - lastY;
//                layout(getLeft() + offetX, getTop() + offetY, getRight() + offetX, getBottom() + offetY);

                //这两个等价于 上面的 layout();
                offsetLeftAndRight(offetX);
                offsetTopAndBottom(offetY);

                break;
            default:
                break;
        }

        return true;//拦截事件

    }
}
