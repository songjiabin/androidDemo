package com.example.mydemo.recyclerview;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.view.FooterView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/07/25
 * desc   :
 * version: 1.0.0
 */

public abstract class WrapperRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List list = new ArrayList<>();

    protected Context context;
    private FootViewHolder footViewHolder;

    /**
     * 脚布局数量
     */
    public int footViewCount = 0;

    /**
     * 脚布局
     */
    private FooterView footView;


    private static final int TYPE_HEADER = 1;
    private static final int TYPE_FOOTER = 3;


    /**
     * 头布局
     * key: Integer; value: object
     */
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();


    public WrapperRecyclerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (position < mHeaderViews.size()) {
            return mHeaderViews.keyAt(position);
        }
        if (position == getItemCount() - 1 && footView != null) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }

        return getItemViewTypeNormal(getRealPosition(position));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return new HeadViewHolder(mHeaderViews.get(viewType));
        } else if (viewType == TYPE_FOOTER) {
            return footViewHolder;
        } else {
            return onCreateViewHolderNormal(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        position = getRealPosition(position);
        onBindViewHolderNormal(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size() + mHeaderViews.size() + footViewCount;
    }



    public int getFootViewCount() {
        return footViewCount;
    }

    public int getState() {
        if (footView != null) {
            return footView.getStatus();
        }
        return 0;
    }

    public void setState(int status) {
        if (footViewHolder != null) {
            footView.setState(status);
        }
    }

    /**
     * @param errorListener 脚布局加载失败
     */
    public void setErrorListener(FooterView.ErrorListener errorListener) {
        footView.setOnErrorListener(errorListener);
    }


    public void addHeaderView(View head) {
        this.mHeaderViews.put(mHeaderViews.size() + TYPE_HEADER, head);
        notifyItemInserted(mHeaderViews.size() - 1);
    }


    public void addFooterView(FooterView footView) {
        this.footView = footView;
        footViewCount++;
        footViewHolder = new FootViewHolder(footView);
        notifyItemInserted(getItemCount() - 1);
    }
    /**
     * 得到真实的position
     *
     * @return
     */
    private int getRealPosition(int position) {
        return position = position - mHeaderViews.size();
    }


    public abstract RecyclerView.ViewHolder onCreateViewHolderNormal(ViewGroup parent, int viewType);

    public abstract void onBindViewHolderNormal(RecyclerView.ViewHolder holder, int position);

    public abstract int getItemViewTypeNormal(int position);

    public void setList(List listData) {
        this.list.clear();
        this.list.addAll(listData);
        this.notifyDataSetChanged();
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }


    }

    class FootViewHolder extends RecyclerView.ViewHolder {


        public FootViewHolder(View itemView) {
            super(itemView);
        }

    }


}
