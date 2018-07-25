package com.example.mydemo.utils;

import android.support.v7.widget.RecyclerView;

import com.example.mydemo.recyclerview.WrapperRecyclerAdapter;
import com.example.mydemo.view.FooterView;

/**
 * author : 宋佳
 * time   : 2018/07/25
 * desc   :
 * Created by cundong on 2015/11/9.
 * <p>
 * 分页展示数据时，RecyclerView的FooterView State 操作工具类
 * <p>
 * RecyclerView一共有几种State：Normal/Loading/Error/TheEnd
 * version: 1.0.0
 */

public class RecyclerViewStateUtils {

    /**
     * 获取当前RecyclerView.FooterView的状态
     *
     * @param recyclerView
     */
    public static int getFooterViewState(RecyclerView recyclerView) {

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof WrapperRecyclerAdapter) {
            if (((WrapperRecyclerAdapter) outerAdapter).getFootViewCount() > 0) {
                return ((WrapperRecyclerAdapter) outerAdapter).getState();
            }
        }

        return FooterView.Normal;
    }

    /**
     * 设置当前RecyclerView.FooterView的状态
     *
     * @param recyclerView
     * @param state
     */
    public static void setFooterViewState(RecyclerView recyclerView, int state) {
        setFooterViewState(recyclerView, state, null);
    }

    /**
     * 设置当前RecyclerView.FooterView的状态
     *
     * @param recyclerView
     * @param state
     * @param errorListener
     */
    public static void setFooterViewState(RecyclerView recyclerView, int state, FooterView.ErrorListener errorListener) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof WrapperRecyclerAdapter) {
            if (((WrapperRecyclerAdapter) outerAdapter).getFootViewCount() > 0) {
                if (state == FooterView.NetWorkError && errorListener != null) {
                    ((WrapperRecyclerAdapter) outerAdapter).setErrorListener(errorListener);
                }
                ((WrapperRecyclerAdapter) outerAdapter).setState(state);
            }
        }
    }
}
