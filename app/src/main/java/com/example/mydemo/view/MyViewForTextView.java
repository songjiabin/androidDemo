package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * author : 宋佳
 * time   : 2018/05/09
 * desc   : 对现有控件进行拓展  一个外面嵌套两个框的 TextView
 * version: 1.0.0
 */

public class MyViewForTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint mPaint2;
    private Paint mPaint1;
    private Paint mpaint;//绘制文本的paint
    private int mViewWith = 0;

    public MyViewForTextView(Context context) {
        super(context);
    }

    public MyViewForTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_green_light));
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);


    }

    public MyViewForTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //在 super之前 写 即： 对TextView来说就是绘制文本之前加入你想做的操作
        //绘制外层矩形
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        //绘制内层矩形
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
        canvas.save(); //保存会画布的状态

        canvas.translate(0, 10); //开始画内容先进行起始点的平移

        super.onDraw(canvas);
        // 在 super之后 写 即： 对TextVie来说就是在绘制文本之后加入你想做的操作
        canvas.restore(); // 返回最新的save状态，即状态2

    }


    /**
     * 给TextView做一个带有闪烁效果的特效
     * <p>
     * 实现这个效果要使用 Paint中 Shader渲染器。通过设置一个不断变化的 LinearGradient。并使用带有该属性的Paint对象来绘制要显示文字。
     * 说白了 要使用Shader渲染器  通过是Paint中的api LinearGradient 来实现。
     * 根据View的宽高设置一个LinearGradient渐变的渲染器
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWith == 0) {
            mViewWith = getMeasuredWidth();
            if (mViewWith > 0) {
                mpaint = getPaint();
                new LinearGradient(0, 0, mViewWith, 0, new int[]{Color.BLUE, 0xffffffff, Color.BLUE}, null, Shader.TileMode.CLAMP);

            }
        }


    }
}
