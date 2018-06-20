package com.example.mydemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.mydemo.R;

import java.lang.reflect.Field;

/**
 * author : 宋佳
 * time   : 2018/06/20
 * desc   :
 * version: 1.0.0
 */

public class SuperEditText extends android.support.v7.widget.AppCompatEditText {
    private Context context;
    private int ic_deleteResID;
    private Drawable ic_delete;
    private int ic_left_clickResID;
    private Drawable ic_left_click;
    private int ic_left_unclickResID;
    private Drawable ic_left_unclick;
    private int lineColor_click;
    private int lineColor_unclick;


    /*
      * 定义属性变量
      * */
    private Paint mPaint; // 画笔
    private int linePosition;
    private int cursor;
    private int color;


    public SuperEditText(Context context) {
        super(context);
        initView(context, null);
    }

    public SuperEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public SuperEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.context = context;

        // 获取控件资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperEditText);

        //删除资源id
        ic_deleteResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_delete, R.drawable.icon_clear);
        // 2. 根据资源ID获取图标资源（转化成Drawable对象）
        ic_delete = context.getResources().getDrawable(ic_deleteResID);

        //左边点击图标（点击的图标)
        ic_left_clickResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_click, R.drawable.ic_left_click);
        ic_left_click = context.getResources().getDrawable(ic_left_clickResID);


        //左边点击图标（点击的图标)
        ic_left_unclickResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_unclick, R.drawable.ic_left_unclick);
        ic_left_unclick = context.getResources().getDrawable(ic_left_unclickResID);


        /**
         * 设置EditText左侧 & 右侧的图片（初始状态仅有左侧图片））
         */
        setCompoundDrawablesWithIntrinsicBounds(ic_left_unclick, null,
                null, null);


        // 默认 = 蓝色#1296db
        int lineColorClick_default = context.getResources().getColor(R.color.lineColor_click);
        // 默认 = 灰色#9b9b9b
        int lineColorunClick_default = context.getResources().getColor(R.color.lineColor_unclick);
        color = lineColorunClick_default;
        lineColor_click = typedArray.getColor(R.styleable.SuperEditText_lineColor_click, lineColorClick_default);
        lineColor_unclick = typedArray.getColor(R.styleable.SuperEditText_lineColor_unclick, lineColorunClick_default);


        /**
         * 初始化光标（颜色 & 粗细）
         */
        // 原理：通过 反射机制 动态设置光标
        // 1. 获取资源ID
        cursor = typedArray.getResourceId(R.styleable.SuperEditText_cursor, R.drawable.cursor);
        try {

            // 2. 通过反射 获取光标属性
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            // 3. 传入资源ID
            f.set(this, cursor);

        } catch (Exception e) {
            e.printStackTrace();
        }


        // 1. 设置画笔
        mPaint = new Paint();
        mPaint.setStrokeWidth(2.0f); // 分割线粗细
        mPaint.setColor(lineColor_unclick); // 分割线默认颜色 = 灰色
        setTextColor(lineColorunClick_default); // 字体默认颜色 = 灰色


        // 3. 分割线位置
        linePosition = typedArray.getInteger(R.styleable.SuperEditText_linePosition, 1);
        // 消除自带下划线
        setBackground(null);


    }


    /**
     * 复写EditText本身的方法：onTextChanged（）
     * 调用时刻：当输入框内容变化时
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setDeleteIconVisible(hasFocus() && text.length() > 0, hasFocus());
        // hasFocus()返回是否获得EditTEXT的焦点，即是否选中
        // setDeleteIconVisible（） = 根据传入的是否选中 & 是否有输入来判断是否显示删除图标->>关注1
    }


    /**
     * 复写EditText本身的方法：onFocusChanged（）
     * 调用时刻：焦点发生变化时
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setDeleteIconVisible(focused && length() > 0, focused);
        // focused = 是否获得焦点
        // 同样根据setDeleteIconVisible（）判断是否要显示删除图标->>关注1
    }


    /**
     * 关注1
     * 作用：判断是否显示删除图标 & 设置分割线颜色
     */
    private void setDeleteIconVisible(boolean deleteVisible, boolean leftVisible) {
        setCompoundDrawablesWithIntrinsicBounds(leftVisible ? ic_left_click : ic_left_unclick, null,
                deleteVisible ? ic_delete : null, null);
        color = leftVisible ? lineColor_click : lineColor_unclick;
        setTextColor(color);
        invalidate();
    }

    /**
     * 作用：绘制分割线
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        setTextColor(color);
        // 绘制分割线
        // 需要考虑：当输入长度超过输入框时，所画的线需要跟随着延伸
        // 解决方案：线的长度 = 控件长度 + 延伸后的长度
        int x = this.getScrollX(); // 获取延伸后的长度
        int w = this.getMeasuredWidth(); // 获取控件长度

        // 传入参数时，线的长度 = 控件长度 + 延伸后的长度
        canvas.drawLine(0, this.getMeasuredHeight() - linePosition, w + x,
                this.getMeasuredHeight() - linePosition, mPaint);

    }

    /**
     * 步骤4：对删除图标区域设置点击事件，即"点击 = 清空搜索框内容"
     * 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
            case MotionEvent.ACTION_UP:
                Drawable drawable = ic_delete;
                if (drawable != null &&
                        event.getX() <= (getWidth() - getPaddingRight())
                        &&
                        event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {
                    setText("");
                }
                // 判断条件说明
                // event.getX() ：抬起时的位置坐标
                // getWidth()：控件的宽度
                // getPaddingRight():删除图标图标右边缘至EditText控件右边缘的距离
                // 即：getWidth() - getPaddingRight() = 删除图标的右边缘坐标 = X1
                // getWidth() - getPaddingRight() - drawable.getBounds().width() = 删除图标左边缘的坐标 = X2
                // 所以X1与X2之间的区域 = 删除图标的区域
                // 当手指抬起的位置在删除图标的区域（X2=<event.getX() <=X1），即视为点击了删除图标 = 清空搜索框内容
                // 具体示意图请看下图
                break;
        }
        return super.onTouchEvent(event);
    }


}
