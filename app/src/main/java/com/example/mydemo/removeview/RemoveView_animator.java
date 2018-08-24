package com.example.mydemo.removeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;

/**
 * author : 宋佳
 * time   : 2018/08/20
 * desc   :
 * version: 1.0.0
 */

public class RemoveView_animator extends View {


    private  Context context;
    private Animation animation;
    private int lastX;
    private int lastY;


    public RemoveView_animator(Context context) {
        this(context,null);
        this.context=context;
    }

    public RemoveView_animator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //得到偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastX;

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }





}
