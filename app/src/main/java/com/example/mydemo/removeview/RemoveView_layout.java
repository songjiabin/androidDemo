package com.example.mydemo.removeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * author : 宋佳
 * time   : 2018/08/20
 * desc   :
 * version: 1.0.0
 */

public class RemoveView_layout extends View {


    private int lastX;
    private int lastY;


    public RemoveView_layout(Context context) {
        super(context);
    }

    public RemoveView_layout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
                //四个参数，left,top,right,bottom
                //使用 layout
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);

                // 使用 offsetLeftAndRight（）与offsetTopAndBottom（）方式
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);


                //使用 LayoutParams 的方式
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                layoutParams.rightMargin = getRight() + offsetX;
                layoutParams.bottomMargin = getBottom() + offsetY;
                setLayoutParams(layoutParams);


                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }
}
