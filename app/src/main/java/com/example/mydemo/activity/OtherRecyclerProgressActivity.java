package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mydemo.R;
import com.example.mydemo.adapter.OnItemClickListener;
import com.example.mydemo.adapter.RecyclerProgressAdapter;
import com.example.mydemo.bean.SuperAdapterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/08/17
 * desc   : https://www.jianshu.com/p/049c21765c14 进度条的复用
 * version: 1.0.0
 */

public class OtherRecyclerProgressActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView rv_show;
    private List<SuperAdapterBean> mList = new ArrayList<>();
    private RecyclerProgressAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohter_recycler_view);
        initView();
    }


    private void initView() {
        rv_show = (RecyclerView) findViewById(R.id.rv_show);
        for (int i = 0; i < 100; i++) {
            SuperAdapterBean bean = new SuperAdapterBean();
            bean.setProgress(i);
            mList.add(bean);
        }

        // 这个没什么好说的
        mAdapter = new RecyclerProgressAdapter(this, mList, R.layout.item_layout_recyclerview_progress);
        rv_show.setLayoutManager(new LinearLayoutManager(this));
        rv_show.setAdapter(mAdapter);



    }

    @Override
    public void onItemClick(int tag) {

    }


}
