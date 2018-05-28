package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.mydemo.utils.ScreenUtils;

/**
 * time   : 2018/05/28
 * desc   :  scrollTo()  scrollBy()方法
 * version: 1.0.0
 */

public class MyScroller2 extends ViewGroup {

    private Scroller mScroller;
    private int lastX;
    private int lastY;
    private Context context;

    public MyScroller2(Context context) {
        super(context);
        initView(context);
    }

    public MyScroller2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyScroller2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    //在本ViewGroup中添加几个 LinearLayout
    private void initView(Context context) {
        this.context = context;
        // 初始化3个 LinearLayout控件
        LinearLayout oneLL = new LinearLayout(context);
        oneLL.setBackgroundColor(Color.RED);
        addView(oneLL);


        LinearLayout twoLL = new LinearLayout(context);
        twoLL.setBackgroundColor(Color.YELLOW);
        addView(twoLL);


        LinearLayout threeLL = new LinearLayout(context);
        threeLL.setBackgroundColor(Color.BLUE);
        addView(threeLL);

    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置该ViewGroup的大小
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);


        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            // 设置每个子视图的大小(LinearLayout) ， 即全屏
            child.measure(ScreenUtils.getScreenWidth(context), ScreenUtils.getScreenHeight(context));
        }
    }

    // layout过程
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        int startLeft = 0; // 每个子视图的起始布局坐标
        int startTop = 10; // 间距设置为10px 相当于 android：marginTop= "10px"
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(startLeft, startTop,
                    startLeft + ScreenUtils.getScreenWidth(context),
                    startTop + ScreenUtils.getScreenHeight(context));
            startLeft = startLeft + ScreenUtils.getScreenWidth(context); //校准每个子View的起始布局位置
            //三个子视图的在屏幕中的分布如下 [0 , 320] / [320,640] / [640,960]
        }
    }


}
