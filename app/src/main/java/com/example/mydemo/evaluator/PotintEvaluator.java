package com.example.mydemo.evaluator;

import android.animation.TypeEvaluator;

import com.example.mydemo.bean.PointBean;

/**
 * author : 宋佳
 * time   : 2018/06/14
 * desc   : 对象的估值器   实现一个圆 从一个点到另一个的过程
 * 估值器（TypeEvaluator）决定 值 的具体变化数值
 * 插值器（Interpolator）决定 值 的变化模式（匀速、加速blabla）
 * version: 1.0.0
 */

public class PotintEvaluator implements TypeEvaluator {


    // 复写evaluate（）
    // 在evaluate（）里写入对象动画过渡的逻辑
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        // 参数说明
        // fraction：表示动画完成度（根据它来计算当前动画的值）
        // startValue、endValue：动画的初始值和结束值

        // 写入对象动画过渡的逻辑


        // 将动画初始值startValue 和 动画结束值endValue 强制类型转换成Point对象

        PointBean startPoint = (PointBean) startValue;
        PointBean endPoint = (PointBean) endValue;

        // 根据fraction来计算当前动画的x和y的值
        //  初始值 过渡 到结束值 的算法是：
        // 1. 用结束值减去初始值，算出它们之间的差值
        // 2. 用上述差值乘以fraction系数
        // 3. 再加上初始值，就得到当前动画的值  startFloat + fraction * (((Number) endValue).floatValue() - startFloat);
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());

        // 将计算后的坐标封装到一个新的Point对象中并返回
        PointBean point = new PointBean(x, y);

        return point;
        // 返回对象动画过渡的逻辑计算后的值
    }
}
