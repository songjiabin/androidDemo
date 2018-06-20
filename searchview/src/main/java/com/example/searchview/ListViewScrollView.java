package com.example.searchview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * author : 宋佳
 * time   : 2018/06/20
 * desc   : ScrollVie 中嵌套的 ListView
 * version: 1.0.0
 */

public class ListViewScrollView extends ListView {


    public ListViewScrollView(Context context) {
        super(context);
    }

    public ListViewScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    // 通过复写其onMeasure方法，达到对ScrollView适配的效果
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
