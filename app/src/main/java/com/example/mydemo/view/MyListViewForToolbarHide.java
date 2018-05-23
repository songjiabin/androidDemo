package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * time   : 2018/05/23
 * desc   : 当listView 上滑动，顶部的ActionBar 或者  ToolBar 会相应的隐藏或者显示
 * version: 1.0.0
 */

public class MyListViewForToolbarHide extends ListView {


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

    }








}
