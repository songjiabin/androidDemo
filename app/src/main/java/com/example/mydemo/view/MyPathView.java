package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/08/08
 * desc   :
 * version: 1.0.0
 */

public class MyPathView extends View {
    private Paint mPaint;

    public MyPathView(Context context) {
        super(context);
    }

    public MyPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setColor(getContext().getResources().getColor(R.color.colorYellow));


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(500, 500);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        RectF rectF = new RectF();
        rectF.set(0, 0, getWidth(), getHeight());

        // Path类将多种复合路径（多个轮廓，如直线段、二次曲线、立方曲线）封装在其内部的几何路径。
        Path path = new Path();
        path.addArc(rectF, 0, 90);



        mPaint.setColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
        canvas.drawArc(rectF, 0, -90, false, mPaint);


        canvas.drawPath(path, mPaint);

    }
}
