package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.mydemo.R;
import com.example.mydemo.utils.ScreenUtils;

/**
 * author : 宋佳
 * time   : 2018/08/06
 * desc   : 自定义雷达 效果图
 * version: 1.0.0
 */

public class RadarView extends View {

    //几边形
    private int count = 5;

    //层数
    private int layerCount = 4;


    private float angle; //每条边对应的圆心角
    private Paint polygonPaint;//边框paint
    private Paint linePaint;
    private Paint txtPaint;
    private Paint circlePaint;
    private Paint regionColorPaint;
    private float radius;// 半径
    private int centerX;//圆心x
    private int centerY;//圆心 y
    private String[] titles = {"dota", "斗地主", "大吉大利，晚上吃鸡", "炉石传说", "跳一跳"};//文字

    private Double[] percents = {0.91, 0.35, 0.12, 0.8, 0.5}; //覆盖区域百分比


    public RadarView(Context context) {
        super(context);
        initView();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        //确定圆心
        //计算圆心角
        angle = (float) (Math.PI * 2 / count);

        //设置画线的 paint
        polygonPaint = new Paint();
        polygonPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorYellow));
        polygonPaint.setAntiAlias(true);
        polygonPaint.setStyle(Paint.Style.STROKE);
        polygonPaint.setStrokeWidth(4f);


        //画线的 paint
        linePaint = new Paint();
        linePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorYellow));
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2f);


        //画文字的 paint
        txtPaint = new Paint();
        txtPaint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        txtPaint.setAntiAlias(true);
        txtPaint.setStyle(Paint.Style.STROKE);
        txtPaint.setTextSize(ScreenUtils.dp2px(getContext(), 12));


        //画圆
        circlePaint = new Paint();
        circlePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        circlePaint.setAntiAlias(true);

        //这个是区域的 paint
        regionColorPaint = new Paint();
        regionColorPaint.setColor(ContextCompat.getColor(getContext(), R.color.color_gray));
        regionColorPaint.setStyle(Paint.Style.FILL);
        regionColorPaint.setAntiAlias(true);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //确定要做这几件事
        // 1、画边
        drawPolygon(canvas);
        //2、画线
        drawLines(canvas);
        //3、画文字
        drawText(canvas);
        //4、覆盖区域
        drawRegion(canvas);

    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        //半径除以层数 =  每次之间的间隔
        float r = radius / layerCount;

        for (int i = 1; i <= layerCount; i++) {

            float curR = r * i; //当前所在层的半径

            //每层需要绘制几条边

            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    //每层的第一个点的坐标  x=圆心  y=圆心y-半径
                    path.moveTo(centerX, centerY - curR);
                } else {
                    //画其他的点
                    //顺时针记录其余顶角的点坐标
                    float x = (float) (centerX + Math.sin(angle * j) * curR);
                    float y = (float) (centerY - Math.cos(angle * j) * curR);
                    path.lineTo(x, y);

                }
            }


            //最外层的顶角外面的五个小圆点(图中红色部分)
            if (i == layerCount) {
                for (int j = 0; j < count; j++) {
                    float x = (float) (centerX + Math.sin(angle * j) * (curR + 12));
                    float y = (float) (centerY - Math.cos(angle * j) * (curR + 12));
                    canvas.drawCircle(x, y, 4, circlePaint);
                }
            }

            //闭合到起始点
            path.close();
            canvas.drawPath(path, polygonPaint);


        }


    }


    //绘制连线
    private void drawLines(Canvas canvas) {
        //间隔  每个正五边形行的间隔
        float r = radius / layerCount;
        for (int i = 0; i < count; i++) {
            //有几条边 就要绘制几条线
            float startX = (float) (centerX + Math.sin(angle * i) * r);
            float startY = (float) (centerY - Math.cos(angle * i) * r);
            //末端坐标
            float endX = (float) (centerX + Math.sin(angle * i) * radius);
            float endY = (float) (centerY - Math.cos(angle * i) * radius);
            canvas.drawLine(startX, startY, endX, endY, linePaint);


        }

    }

    private void drawText(Canvas canvas) {
        for (int i = 0; i < count; i++) {
            //获取到雷达图最外边的坐标
            float x = (float) (centerX + Math.sin(angle * i) * (radius + 12));
            float y = (float) (centerY - Math.cos(angle * i) * (radius + 12));
            if (angle * i == 0) {
                //第一个文字位于顶角正上方
                txtPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(titles[i], x, y - 18, txtPaint);
                txtPaint.setTextAlign(Paint.Align.LEFT);
            } else if (angle * i > 0 && angle * i < Math.PI / 2) {
                //微调
                canvas.drawText(titles[i], x + 18, y + 10, txtPaint);
            } else if (angle * i >= Math.PI / 2 && angle * i < Math.PI) {
                //最右下的文字获取到文字的长、宽，按文字长度百分比向左移
                String txt = titles[i];
                Rect bounds = new Rect();
                txtPaint.getTextBounds(txt, 0, txt.length(), bounds);
                float height = bounds.bottom - bounds.top;
                float width = txtPaint.measureText(txt);
                canvas.drawText(txt, x - width * 0.4f, y + height + 18, txtPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {
                //同理最左下的文字获取到文字的长、宽，按文字长度百分比向左移
                String txt = titles[i];
                Rect bounds = new Rect();
                txtPaint.getTextBounds(txt, 0, txt.length(), bounds);
                float width = txtPaint.measureText(txt);
                float height = bounds.bottom - bounds.top;
                canvas.drawText(txt, x - width * 0.6f, y + height + 18, txtPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i < 2 * Math.PI) {
                //文字向左移动
                String txt = titles[i];
                float width = txtPaint.measureText(txt);
                canvas.drawText(txt, x - width - 18, y + 10, txtPaint);
            }

        }


    }

    //覆盖区域的
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        float r = radius / layerCount;//每层的间距
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                path.moveTo(centerX, (float) (centerY - r - (radius - r) * percents[i]));
            } else {
                float x = (float) (centerX + Math.sin(angle * i) * (percents[i] * (radius - r) + r));
                float y = (float) (centerY - Math.cos(angle * i) * (percents[i] * (radius - r) + r));
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, regionColorPaint);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(h, w) / 2 * 0.7f;
        centerX = w / 2;
        centerY = h / 2;

    }
}
