package com.example.mydemo.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mydemo.utils.ScreenUtils;

/**
 * time   : 2018/05/23
 * desc   : 当listView 上滑动，顶部的ActionBar 或者  ToolBar 会相应的隐藏或者显示
 * version: 1.0.0
 */

public class MyListViewForToolbarHide extends ListView implements View.OnTouchListener {


    private int mTouchSlop;
    private float mFirsty;
    private float mCurrentY;
    private int direction; //方向
    private boolean mShow = true;//是否显示toolbar
    private Animator mAnimator;
    private Toolbar mToolbar;

    public MyListViewForToolbarHide(Context context) {
        super(context);
        initView(context);
    }

    public MyListViewForToolbarHide(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyListViewForToolbarHide(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //手动添加一个ListView  header 为了能显示 listview中的第一个
        View header = new View(context);
        //设置Header 宽高度
        header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(context, 50)));
        this.addHeaderView(header);
        //定义一个mTouchSlop变量用来获取系统认为的最低滑动距离。 即：超过这个距离的移动，系统将其定义为滑动状态了
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();


    }

    /**
     * 借助View的 OnTouchListener 来监听ListView的滑动
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下的时候
                mFirsty = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的时候
                mCurrentY = event.getY();
                if (mCurrentY - mFirsty > mTouchSlop) {
                    //向下滑
                    direction = 0;
                } else if (mFirsty - mCurrentY > 0) {
                    //向上滑
                    direction = 1;
                }
                if (direction == 1) {
                    //向上滑
                    if (mShow) {
                        toolbarAnim(1);//hide
                        mShow = !mShow;
                    }
                } else if (direction == 0) {
                    if (!mShow) {
                        toolbarAnim(0);//show
                        mShow = !mShow;
                    }
                }
                break;
            default:
                break;
        }
        return false;
    }


    private void toolbarAnim(int flag) {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (flag == 0) {
            //show
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "translationY", mToolbar.getTranslationY(), 0);
        } else {
            // hide
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "translationY", mToolbar.getTranslationY(),
                    -mToolbar.getHeight());
        }
        mAnimator.start();
    }


    public void setToolbar(Toolbar toolbar) {
        this.mToolbar = toolbar;
    }
}
