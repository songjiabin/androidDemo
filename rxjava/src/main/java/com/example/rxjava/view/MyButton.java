package com.example.rxjava.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * author : 宋佳
 * time   : 2018/07/16
 * desc   :
 * version: 1.0.0
 */

public class MyButton extends android.support.v7.widget.AppCompatButton {


    public MyButton(Context context) {
        super(context);
        initView(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 这种模式表示重复最后一种颜色直到该View结束的地方，如果我设置着色器开始的位置为（0，0），结束位置为（getMeasuredWidth(), 0）表示我的着色器要给整个View在水平方向上渲染不同的颜色
        Paint paint = new Paint();
        LinearGradient linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0,
                new int[]{Color.RED, Color.WHITE, Color.BLUE}, null, LinearGradient.TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

    }
}
