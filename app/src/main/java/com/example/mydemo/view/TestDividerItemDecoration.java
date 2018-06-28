package com.example.mydemo.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author : 宋佳
 * time   : 2018/06/22
 * desc   :
 * version: 1.0.0
 */

public class TestDividerItemDecoration extends  RecyclerView.ItemDecoration  {


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
