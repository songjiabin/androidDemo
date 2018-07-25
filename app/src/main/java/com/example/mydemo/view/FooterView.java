package com.example.mydemo.view;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/07/24
 * desc   :
 * version: 1.0.0
 */

public class FooterView extends RelativeLayout {


    private Context context;
    private View footerView;
    private ProgressBar pb;
    private TextView tv;



    private int status;

    private ErrorListener errorListener;
    public static final int Normal = 0x01;
    public static final int TheEnd = 0x02;
    public static final int Loading = 0x03;
    public static final int NetWorkError = 0x04;

    public FooterView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        footerView = inflate(context, R.layout.item_footview, this);
        pb = (ProgressBar) footerView.findViewById(R.id.item_footer_view_pb);
        tv = (TextView) footerView.findViewById(R.id.item_footer_view_tv);

    }

    public int getStatus() {
        return status;
    }


    /**
     * 展示FooterView
     *
     * @param status 状态值
     */
    public void setState(int status) {
        this.status = status;
        switch (status) {
            case Loading:
                setVisibility(View.VISIBLE);
                pb.setVisibility(View.VISIBLE);
                tv.setText("正在加载更多...");
                setOnClickListener(null);
                break;
            case TheEnd:
                setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                tv.setText("没有更多了");
                setOnClickListener(null);
                break;
            case NetWorkError:
                setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                tv.setText("加载出错，点击重试");
                setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (errorListener != null) {
                            errorListener.errorListener();
                        }
                    }
                });
                break;
            case Normal:
                setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                tv.setText("更多");
                setOnClickListener(null);
                break;
        }
    }



    public void setOnErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public interface ErrorListener {

        void errorListener();
    }
}
