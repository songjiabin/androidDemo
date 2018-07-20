package com.example.mydemo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.bean.BankModel;
import com.example.mydemo.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/07/19
 * desc   :
 * version: 1.0.0
 */

public class BankPickerView extends ScrollView {


    private Context context;
    private LinearLayout views;
    private Runnable scrollerTask;
    //延迟时间
    private int newCheck = 50;


    private int displayItemCount; // 每页显示的数量


    //初始化 Y
    private int initialY;

    //每个 item的高
    private int itemHeight = 0;


    private int offset; // 偏移量（需要在最前面和最后面补全）

    public static final int OFF_SET_DEFAULT = 1;

    private List<BankModel> items;

    private int scrollDirection = -1;
    private static final int SCROLL_DIRECTION_UP = 0;
    private static final int SCROLL_DIRECTION_DOWN = 1;


    private int selectedIndex = 1;
    private Paint paint;


    private int viewWidth;


    /**
     * 获取选中区域的边界
     */
    private int[] selectedAreaBorder;


    public BankPickerView(Context context) {
        super(context);
        initView(context);
        offset = OFF_SET_DEFAULT;
    }

    public BankPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        offset = OFF_SET_DEFAULT;
    }

    public BankPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        offset = OFF_SET_DEFAULT;
    }


    private void initView(Context context) {
        this.context = context;
        //设置 scrollbar 没有
        this.setVerticalScrollBarEnabled(false);
        //初始化 承载 子View 的父类
        views = new LinearLayout(context);
        views.setOrientation(LinearLayout.VERTICAL);
        this.addView(views);
        scrollerTask = new Runnable() {
            @Override
            public void run() {
                int newY = getScrollY();
                if (initialY - newY == 0) {
                    //初始化的Y -  新的Y ==0 表示  stoped

                    //剩余的
                    final int remainder = initialY % itemHeight;
                    final int divided = initialY / itemHeight;

                    if (remainder == 0) {
                        selectedIndex = divided + offset;
                        onSelectedCallBack();
                    } else {
                        if (remainder > itemHeight / 2) {
                            //滑动的下一个
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    smoothScrollTo(0, initialY - remainder + itemHeight);
                                    selectedIndex = divided + offset + 1;
                                    onSelectedCallBack();
                                }
                            });
                        } else {
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    //滑动的上一个
                                    smoothScrollTo(0, initialY - remainder);
                                    selectedIndex = divided + offset;
                                    onSelectedCallBack();
                                }
                            });
                        }
                    }


                } else {
                    initialY = getScrollY();
                    postDelayed(scrollerTask, newCheck);
                }
            }
        };
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画线

        if (viewWidth == 0 && context != null) {
            viewWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        }

        if (null == paint) {
            paint = new Paint();
            paint.setColor(getResources().getColor(android.R.color.holo_red_dark));
            paint.setStrokeWidth(ScreenUtils.dp2px(getContext(), 0.5f));
        }


        this.setBackground(new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                canvas.drawLine(0, obtainSelectedAreaBorder()[0], viewWidth, obtainSelectedAreaBorder()[0], paint);
                canvas.drawLine(0, obtainSelectedAreaBorder()[1], viewWidth, obtainSelectedAreaBorder()[1], paint);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        });








    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            startScrollerTask();
            this.postDelayed(scrollerTask, newCheck);
        }
        return super.onTouchEvent(ev);
    }


    public void startScrollerTask() {
        initialY = getScrollY();
        this.postDelayed(scrollerTask, newCheck);
    }

    //初始化数据
    public void setData(List<BankModel> list) {

        Log.i("宋佳宾", "setData");

        if (null == items) {
            items = new ArrayList<>();
        }

        items.clear();
        items.addAll(list);


        // 前面和后面补全
        for (int i = 0; i < offset; i++) {
            // 添加一条空数据
            items.add(0, null);
            items.add(null);
        }


        initData();

    }
    public List<BankModel> getData() {
        return items;
    }

    private void initData() {
        //每页显示的数量
        displayItemCount = offset * 2 + 1;
        //移除里面的 滑动的View
        views.removeAllViews();
        for (BankModel item : items) {
            views.addView(createView(item));
        }

        refreshItemView(0);


    }


    //创建 item的 view 设置其高 各种属性
    private View createView(BankModel item) {
        View view = View.inflate(getContext(), R.layout.item_bank_info, null);
        ImageView logo = (ImageView) view.findViewById(R.id.iv_bank_logo);
        TextView name = (TextView) view.findViewById(R.id.tv_bank_name);
        TextView desc = (TextView) view.findViewById(R.id.tv_bank_description);


        if (item != null) {
            Glide.with(getContext()).load(item.bankLogo).into(logo);
            name.setText(item.bankName);
            desc.setText(item.bankDesc);
        }


        if (0 == itemHeight) {
            //设置 里面View的 高
            itemHeight = getViewMeasuredHeight(view);
            //设置 Linearlayout的高
            views.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayItemCount));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
            this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, itemHeight * displayItemCount));
        }


        return view;

    }


    private int getViewMeasuredHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredHeight();
    }


    /**
     * 刷新View
     *
     * @param y
     */
    private void refreshItemView(int y) {

        // 滚动的位置
        int position = y / itemHeight + offset;

        //剩余的
        int remainder = y % itemHeight;

        int divided = y / itemHeight;

        //表示刚开始滑动还没有到达第一个
        if (remainder == 0) {
            position = divided + offset;
        } else {
            if (remainder > itemHeight / 2) {
                position = divided + offset + 1;
            }
        }


        int childSize = views.getChildCount();

        for (int i = 0; i < childSize; i++) {
            if (i == position) {
                //滚动位置的 item
                View itemView = views.getChildAt(i);
                LinearLayout ll_background = (LinearLayout) itemView.findViewById(R.id.ll_backgroud);
                if (itemView != null) {
                    //设置为不透明
                    itemView.setAlpha(1f);
                    ll_background.setBackground(null);
                }
            } else if (i == position - 1 || i == position + 1) {
                //表示为中间的上下两个
                View view1 = views.getChildAt(position - 1);
                View view2 = views.getChildAt(position + 1);
                LinearLayout ll_background1 = (LinearLayout) view1.findViewById(R.id.ll_backgroud);
                LinearLayout ll_background2 = (LinearLayout) view2.findViewById(R.id.ll_backgroud);

                if (view1 != null) {
                    view1.setAlpha(0.65f);
                    ll_background1.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
                }
                if (view2 != null) {
                    view2.setAlpha(0.65f);
                    ll_background2.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
                }


            } else {
                View itemView = views.getChildAt(i);
                LinearLayout ll_background = (LinearLayout) itemView.findViewById(R.id.ll_backgroud);
                if (itemView != null) {
                    itemView.setAlpha(0.4f);
                    ll_background.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
                }
            }


        }
    }


    //当滚动的时候
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        refreshItemView(t);
        if (t > oldt) {
//            Log.d(TAG, "向下滚动");
            scrollDirection = SCROLL_DIRECTION_DOWN;
        } else {
//            Log.d(TAG, "向上滚动");
            scrollDirection = SCROLL_DIRECTION_UP;

        }
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        setBackgroundDrawable(null);
        Log.i("宋佳宾", "onSizeChanged");


    }


    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 3);
    }



    public void setSelection(int position) {
        final int p = position;
        selectedIndex = p + offset;
        this.post(new Runnable() {
            @Override
            public void run() {
                smoothScrollTo(0, p * itemHeight);
            }
        });
    }


    private int[] obtainSelectedAreaBorder() {
        if (null == selectedAreaBorder) {
            selectedAreaBorder = new int[2];
            selectedAreaBorder[0] = itemHeight * offset;
            selectedAreaBorder[1] = itemHeight * (offset + 1);
        }
        return selectedAreaBorder;
    }


    private OnBankSelectedListener onBankSelectedListener;

    public void setOnBankSelectedListener(OnBankSelectedListener onBankSelectedListener) {
        this.onBankSelectedListener = onBankSelectedListener;
    }


    public interface OnBankSelectedListener {
        void onSelected(int selectedIndex, BankModel item);
    }


    /**
     * 选中回调
     */
    private void onSelectedCallBack() {
        if (null != onBankSelectedListener && selectedIndex < items.size()) {
            onBankSelectedListener.onSelected(selectedIndex, items.get(selectedIndex));
        }

    }
}
