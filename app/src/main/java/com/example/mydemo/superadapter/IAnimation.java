package com.example.mydemo.superadapter;

import android.support.v7.widget.RecyclerView;

import com.example.mydemo.superadapter.animation.BaseAnimation;


/**
 * Animation interface for adapter.
 * <p>
 * Created by Cheney on 16/6/28.
 */
interface IAnimation {

    void enableLoadAnimation();

    void enableLoadAnimation(long duration, BaseAnimation animation);

    void cancelLoadAnimation();

    void setOnlyOnce(boolean onlyOnce);

    void addLoadAnimation(RecyclerView.ViewHolder holder);

}
