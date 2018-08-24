package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mydemo.R;
import com.example.mydemo.adapter.BaseAdapter;
import com.example.mydemo.adapter.BaseAdapter.BaseAdapterListener;
import com.example.mydemo.adapter.BaseViewHolder;
import com.example.mydemo.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/08/17
 * desc   : 原文地址  不用数据的标识符 来进行item的复用
 * version: 1.0.0
 */

public class OtherRecyclerViewActivity extends AppCompatActivity implements OnItemClickListener, BaseAdapterListener<String> {


    private RecyclerView rv_show;
    private List<String> mList = new ArrayList<>();
    private BaseAdapter mAdapter;
    private int currentPosition = -1;        // 当前的位置
    private int prePosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohter_recycler_view);
        initView();
    }

    private void initView() {
        rv_show = (RecyclerView) findViewById(R.id.rv_show);
        for (int i = 0; i < 100; i++) {
            mList.add("");
        }

        // 这个没什么好说的
        mAdapter = new BaseAdapter(mList, this, R.layout.item_layout_recyclerview, this);
        rv_show.setLayoutManager(new LinearLayoutManager(this));
        rv_show.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(int position) {


        // TODO: 2018/4/18 如果是相同的不设置
        if (prePosition != position) {
            currentPosition = position;
            mAdapter.notifyItemChanged(position);
            mAdapter.notifyItemChanged(prePosition);
            prePosition = position;
        }
    }


    @Override
    public void convert(BaseViewHolder holder, String o) {

       /* //得到 当前的位置
        int layoutPosition = holder.getLayoutPosition();

        // 设置最后一个的位置
        holder.setVisible(R.id.view, layoutPosition == mList.size() - 1);

        ImageView iv_select = holder.getView(R.id.iv_select);
        //设置checkbox显示
        holder.setVisible(R.id.iv_select);
        //当点击的是目标的时候 进行选中
        if (layoutPosition == currentPosition) {
            iv_select.setImageResource(R.drawable.checkbox_pressed);
        } else {
            iv_select.setImageResource(R.drawable.checkbox_normal);
        }*/


        int layoutPosition = holder.getLayoutPosition();

        // 设置最后一个的位置
        holder.setVisible(R.id.view, layoutPosition == mList.size() - 1);

//        ImageView iv_select = holder.getView(R.id.iv_select);

        ImageView iv_select = (ImageView) holder.getThis(R.id.iv_select);

        iv_select.setVisibility(View.VISIBLE);


        //设置checkbox显示
//        holder.setVisible(R.id.iv_select);
        //当点击的是目标的时候 进行选中
        if (layoutPosition == currentPosition) {
            iv_select.setImageResource(R.drawable.checkbox_pressed);
        } else {
            iv_select.setImageResource(R.drawable.checkbox_normal);
        }


    }
}
