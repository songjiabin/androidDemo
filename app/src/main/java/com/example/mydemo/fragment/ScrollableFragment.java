package com.example.mydemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.mydemo.R;
import com.example.mydemo.activity.WeiboActivity;
import com.example.mydemo.adapter.FragmentAdapter;
import com.example.mydemo.bean.MainBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/07/23
 * desc   :
 * version: 1.0.0
 */

public class ScrollableFragment extends android.support.v4.app.Fragment {


    private View view;
    private WeiboActivity activity;
    private RecyclerView recyclerView;
    private ScrollView scrollView;
    private RecyclerViewHeader header;
    private FragmentAdapter adapter;
    List<MainBean> data = new ArrayList<>();
    MainBean bean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        view = View.inflate(getActivity(), R.layout.fragment_listview, null);

        initView();
        activity = (WeiboActivity) getActivity();
        initAdapter();
        initData();
        initonScroll();




        return view;
    }


    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        header = (RecyclerViewHeader) view.findViewById(R.id.header);
    }


    private void initAdapter() {
        View headerView = View.inflate(getContext(), R.layout.view_header, null);
        RelativeLayout relativeLayout = (RelativeLayout) headerView.findViewById(R.id.header_title);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        header.addView(headerView);
//        header.attachTo(recyclerView);
        adapter = new FragmentAdapter(data, getActivity());
        //分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


    private void initData() {

        for (int i = 0; i < 10; i++) {
            data.add(i, bean);
        }
        adapter.notifyDataSetChanged();
    }


    private void initonScroll() {
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                Log.e("i======", "i:" + i + ",i1:" + i1 + ",i2:" + i2 + ",i3:" + i3);
                if (i1 > 300) {
                    activity.getonScroll(i1);
                }
            }
        });
        //这里获取不到recycler的监听事件，因为ScrollView冲突问题，在布局将滚动焦点交给了ScrollView处理
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("d======", dx + "," + dy);

            }
        });
    }
}
