package com.example.mydemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mydemo.R;

/**
 * 创建复合型控件
 */

public class MyToolBar extends RelativeLayout {
    private Context context;
    private String mTitleText, mRightText, mLeftText;
    private int mLeftColor, mTitleColor, mRightColor;
    private Drawable mLeftBackground, mRightBackground;
    private float mTitleSize;
    private Button mLeftButton, mRightButton;
    private TextView mTitleTextView;
    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;


    public MyToolBar(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public MyToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyToolBar);
        mTitleText = typedArray.getString(R.styleable.MyToolBar_title);
        mLeftText = typedArray.getString(R.styleable.MyToolBar_leftText);
        mRightText = typedArray.getString(R.styleable.MyToolBar_rightText);

        mLeftColor = typedArray.getColor(R.styleable.MyToolBar_leftTextColor, 0);
        mRightColor = typedArray.getColor(R.styleable.MyToolBar_rightTextColor, 0);
        mTitleColor = typedArray.getColor(R.styleable.MyToolBar_titleTextColor, 0);


        mLeftBackground = typedArray.getDrawable(R.styleable.MyToolBar_leftBackground);
        mRightBackground = typedArray.getDrawable(R.styleable.MyToolBar_rightBackground);


        mTitleSize = typedArray.getDimension(R.styleable.MyToolBar_titleTextSize, 10);


        //recycle(); 方法避免重新创建的时候的错误   完成资源的回收
        typedArray.recycle();

        initView();
        initListener();


    }

    /**
     * 初始化View
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleTextView = new TextView(context);

        //为创建的组件赋值
        mLeftButton.setText(mLeftText);
        mRightButton.setText(mRightText);
        mTitleTextView.setText(mTitleText);


        mLeftButton.setTextColor(mLeftColor);
        mRightButton.setTextColor(mRightColor);

        mLeftButton.setBackground(mLeftBackground);
        mRightButton.setBackground(mRightBackground);


        mTitleTextView.setTextSize(mTitleSize);
        mTitleTextView.setTextColor(mTitleColor);
        mTitleTextView.setGravity(Gravity.CENTER);


        //为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //添加此到父View中
        this.addView(mLeftButton, mLeftParams);


        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        //添加此到父View中
        this.addView(mRightButton, mRightParams);


        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        this.addView(mTitleTextView, mTitleParams);
    }


    private void initListener() {
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolBarClick.liftClick();
            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolBarClick.rightClick();
            }
        });
    }

    public toolbarClickListener mToolBarClick;

    //暴露一个方法给调用者来注册借口回调
    public void setmToolBarClick(toolbarClickListener mToolBarClick) {
        this.mToolBarClick = mToolBarClick;
    }

    public interface toolbarClickListener {
        //左边按钮的点击事件
        void liftClick();

        //右边按钮的点击事件
        void rightClick();
    }


}
