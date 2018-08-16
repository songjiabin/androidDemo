package com.example.mydemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * author : 宋佳
 * time   : 2018/08/14
 * desc   :
 * version: 1.0.0
 */

public class PopularController {


    private CommonPopularWindow popuWindow;
    private Context context;
    public View contentView;
    private Window mWindow;

    public PopularController(Context context, CommonPopularWindow commonPopularWindow) {
        this.context = context;
        this.popuWindow = commonPopularWindow;
    }


    /**
     * 创建 View
     */
    public void createView(int view) {
        this.contentView = LayoutInflater.from(context).inflate(view, null);
        this.popuWindow.setContentView(this.contentView);
    }

    /**
     * 创建 View
     */
    public void createView(View view) {
        this.contentView = null;
        this.contentView = view;
        this.popuWindow.setContentView(this.contentView);
    }


    /**
     * 设置popular的宽和高
     *
     * @param width
     * @param height
     */
    public void setPopuWindowWihthAndHeight(int width, int height) {
        if (width == 0 || height == 0) {
            this.popuWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            this.popuWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            this.popuWindow.setWidth(width);
            this.popuWindow.setHeight(height);
        }
    }


    /**
     * 设置 popular的透明度
     * 当弹框的时候 改变背景颜色
     * 改变的是 window 透明度
     *
     * @param level
     */
    public void setBackGroundLevel(float level) {
        mWindow = ((Activity) context).getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.alpha = level;
        mWindow.setAttributes(params);
    }


    /**
     * 设置popular动画
     *
     * @param animationStyle
     */
    public void setAnimationStyle(int animationStyle) {
        this.popuWindow.setAnimationStyle(animationStyle);
    }


    /**
     * 设置popular外部是否可以点击
     *
     * @param touchable
     */
    public void setOutSideTouchable(boolean touchable) {
        //设置是否可点击的时候必须先设置透明度
        this.popuWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置透明度
        this.popuWindow.setOutsideTouchable(touchable);
        this.popuWindow.setFocusable(touchable);
    }


    public void setDissmis() {
        this.popuWindow.dismiss();
    }


    public static class PopularParams {

        public int layoutResId;//布局id
        public int mWidth, mHeight;//弹窗的宽和高
        public float bg_level;//屏幕背景灰色程度
        public int animationStyle;//动画Id
        public View mView;//布局View
        public boolean isTouchable = true;//是否可以点击


        public Context context;

        public PopularParams(Context context) {
            this.context = context;
        }


        public void apply(PopularController popularController) {

            if (mView != null) {
                popularController.createView(mView);
            } else if (layoutResId != 0) {
                popularController.createView(layoutResId);
            }


            popularController.setPopuWindowWihthAndHeight(mWidth, mWidth);

            popularController.setOutSideTouchable(isTouchable);


            popularController.setBackGroundLevel(bg_level);
            popularController.setAnimationStyle(animationStyle);


        }


    }


}
