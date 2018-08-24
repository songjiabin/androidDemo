package com.example.mydemo.removeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : 宋佳
 * time   : 2018/08/21
 * desc   :
 * version: 1.0.0
 */

public class ScrollByView extends View {


    public ScrollByView(Context context) {
        super(context);
    }

    public ScrollByView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollByView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int lastX = 0;
    private int lastY = 0;

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
                int offsetX = x - lastX;
                int offsetY = y - lastX;
                ViewGroup viewGroup = (ViewGroup) ScrollByView.this.getParent();

                viewGroup.scrollBy(-offsetX, -offsetY);
                break;
            default:
                break;
        }

        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}
