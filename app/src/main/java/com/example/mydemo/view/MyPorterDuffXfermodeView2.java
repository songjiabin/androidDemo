package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/05/30
 * desc   :  类似于刮刮乐的效果
 * version: 1.0.0
 */

public class MyPorterDuffXfermodeView2 extends View {
    private Paint mPaint;
    private Path mPath;
    private Bitmap mBgBitmap;
    private Bitmap mFgBitmap;
    private Canvas mCanvas;
    private Context context;

    public MyPorterDuffXfermodeView2(Context context) {
        super(context);
        initView(context);
    }

    public MyPorterDuffXfermodeView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyPorterDuffXfermodeView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        mPaint = new Paint();
        // 这里进行设置透明
        mPaint.setAlpha(0);
        //此模式是进行覆盖
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        //设置边界椭圆
        mPaint.setStrokeWidth(50);
        //使 笔触和连接处能更加的圆滑些  即：setStrokeJoin  setStrokeCap
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();
        //底下的图层
        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        //上面的灰色 图层
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);


    }

    /**
     * 获取用户滑动所产生的路径 并使用path保存用户手指划过的路径
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                //移动
                mPath.lineTo(event.getX(), event.getY());
                break;
            default:
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(mBgBitmap, 0, 0, null);
        canvas.drawBitmap(mFgBitmap, 0, 0, null);
    }
}
