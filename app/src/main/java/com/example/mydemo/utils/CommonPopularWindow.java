package com.example.mydemo.utils;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * author : 宋佳
 * time   : 2018/08/14
 * desc   :
 * version: 1.0.0
 */

public class CommonPopularWindow extends PopupWindow {


    private PopularController popularController;

    public CommonPopularWindow(Context context) {
        super(context);
        this.popularController = new PopularController(context, this);
    }


    /**
     * 当popular 关闭的时候
     */
    @Override
    public void dismiss() {
        super.dismiss();
        //将背景恢复
        this.popularController.setBackGroundLevel(1.0f);
    }


    @Override
    public int getWidth() {
        return popularController.contentView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return popularController.contentView.getMeasuredHeight();
    }


    public static class Builder {


        private PopularController.PopularParams params;
        private ViewInterface listener;



        public Builder(Context context) {
            params = new PopularController.PopularParams(context);
        }


        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }


        public Builder setView(View view) {
            params.layoutResId = 0;
            params.mView = view;
            return this;
        }


        public Builder setWidthAndHeigth(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }


        public Builder setBackGroundLevel(float level) {
            params.bg_level = level;
            return this;
        }

        public Builder setOutsideTouchable(boolean toubalbe) {
            params.isTouchable = toubalbe;
            return this;
        }

        public Builder setAnimationStyle(int animationStyle) {
            params.animationStyle = animationStyle;
            return this;
        }

        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        public CommonPopularWindow create() {
            CommonPopularWindow myCommonPopularWindow = new CommonPopularWindow(params.context);
            params.apply(myCommonPopularWindow.popularController);
            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(myCommonPopularWindow.popularController.contentView, params.layoutResId);
            }
            CommonUtil.measureWidthAndHeight(myCommonPopularWindow.popularController.contentView);
            return myCommonPopularWindow;
        }

    }


    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }


}
