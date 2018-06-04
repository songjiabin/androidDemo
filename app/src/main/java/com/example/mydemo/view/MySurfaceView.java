package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * author : 宋佳
 * time   : 2018/06/04
 * desc   : SurfaceView
 * version: 1.0.0
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {


    private SurfaceHolder mHolder;

    //用于绘制的Canvas
    private Canvas mCanvas;
    //子线程标志位  用来控制子线程的
    private boolean mIsDrawing;


    public MySurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        //初始化 SurfaceHolder 并注册 SurfaceHolder的回调方法
        mHolder = getHolder();
        mHolder.addCallback(this);
        //设置焦点
        setFocusable(true);
        //
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // SurfaceView的创建
        mIsDrawing = true;
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // SurfaceView的改变
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // SurfaceView的销毁
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            //开始绘制
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                //每次绘制完 开始提交
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }
}
