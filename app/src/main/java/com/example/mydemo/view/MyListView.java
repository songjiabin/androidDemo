package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.mydemo.utils.ScreenUtils;
import com.orhanobut.logger.Logger;

/**
 * 在原有 ListView上进行拓展
 * 具有弹性的ListView
 */
public class MyListView extends ListView implements View.OnTouchListener, AbsListView.OnScrollListener {


    private int lastVisibleItemPostion;

    private int mMaxOverDistance;


    public MyListView(Context context) {
        super(context);
        initView(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mMaxOverDistance = ScreenUtils.dp2px(context, 50);
        this.setOnTouchListener(this);
        this.setOnScrollListener(this);
        DisplayMetrics metrices = context.getResources().getDisplayMetrics();
        float density = metrices.density;
        mMaxOverDistance = (int) (density * mMaxOverDistance);

    }


    /**
     * View的监听事件
     * 通过 ACTION_DOWN、 ACTION_UP 、 ACTION_MOVE 这三个发生的坐标就能确定用户滑动的方向
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventAction = event.getAction();
        Log.i("sjb", "onTouch" + eventAction);
        if (eventAction == MotionEvent.ACTION_UP) {
            //抬起的时候
        } else if (eventAction == MotionEvent.ACTION_DOWN) {
            //按下的时候
        } else if (eventAction == MotionEvent.ACTION_MOVE) {
            //移动的时候
        }
        return false;//true 拦截  false不拦截
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("sjb", "onTouchEvent" + ev.getAction() + "");
        return super.onTouchEvent(ev);
    }


    /**
     * AbsListView listView 的监听事件   封装了很多与ListView相关的信息。
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case OnScrollListener.SCROLL_STATE_IDLE:
                //滑动停止的时候
                Logger.d("滑动停止的时候");
                break;

            case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                //正在滚动的时候
                Logger.d("正在滚动的时候");
                break;
            case OnScrollListener.SCROLL_STATE_FLING:
                //手指抛动时  即 手指用力滑动
                //在离开 ListView 由于惯性继续滑动
                Logger.d("在离开 ListView 由于惯性继续滑动");

            default:
                break;
        }
    }

    /**
     * AbsListView listView 的监听事件   封装了很多与ListView相关的信息。
     *
     * @param view
     * @param firstVisibleItem 当前能看到的第一个item的id
     * @param visibleItemCount 当前能都看到的item的数量
     * @param totalItemCount   整个 item的数量
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //当滚动的时候 一直是被调用的
        if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
            //这种情况的话 说明已经滑到了最后一行
            Logger.d("已经到了底部了");
        }

        if (firstVisibleItem > lastVisibleItemPostion) {
            //上滑
            Logger.d("上滑");
        } else if (firstVisibleItem < lastVisibleItemPostion) {
            //下滑
            Logger.d("下滑");
        }

        lastVisibleItemPostion = firstVisibleItem;

    }

    /**
     * 控制滑动到边缘的处理方法
     *
     * @param deltaX
     * @param deltaY
     * @param scrollX
     * @param scrollY
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX
     * @param maxOverScrollY 在任一方向上过度滚动的像素数   沿着Y轴。 就是可以再Y轴上 可以弹性的距离
     * @param isTouchEvent
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }
}
