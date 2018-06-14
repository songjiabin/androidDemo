package com.example.mydemo.bean;

/**
 * author : 宋佳
 * time   : 2018/06/14
 * desc   : 圆点的坐标
 * version: 1.0.0
 */

public class PointBean {
    // 设置两个变量用于记录坐标的位置
    private float x;
    private float y;

    // 构造方法用于设置坐标
    public PointBean(float x, float y) {
        this.x = x;
        this.y = y;
    }



    // get方法用于获取坐标
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


}
