package com.example.mydemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.mydemo.R;
import com.example.mydemo.activity.WeiboActivity;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        view = View.inflate(getActivity(), R.layout.fragment_listview, null);

        initView();
        activity = (WeiboActivity) getActivity();
        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        header = (RecyclerViewHeader) view.findViewById(R.id.header);
    }


}
