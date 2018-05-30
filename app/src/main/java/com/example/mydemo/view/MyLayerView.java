package com.example.mydemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * time   : 2018/05/30
 * desc   : Layer图层
 * version: 1.0.0
 */

public class MyLayerView extends View {

    private static final int LAYER_FLAGS =
            Canvas.MATRIX_SAVE_FLAG |
                    Canvas.CLIP_SAVE_FLAG |
                    Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                    Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                    Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    public MyLayerView(Context context) {
        super(context);
        initView(context);
    }

    private Paint mPaint;

    public MyLayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(150, 150, 100, mPaint);
        canvas.save();


        //新建另外一个图层   alpha:为透明度：
        //  127 为半通明
        //  255 完全不透明
        //  0 为完全透明
        canvas.saveLayerAlpha(0, 0, 400, 400, 255, LAYER_FLAGS);
        mPaint.setColor(Color.RED);

        canvas.drawCircle(200, 200, 100, mPaint);

        canvas.restore();


    }
}
