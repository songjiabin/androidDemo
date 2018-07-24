package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.view.FooterView;
import com.example.mydemo.view.UserTitle;

/**
 * author : 宋佳
 * time   : 2018/07/24
 * desc   : 实现  仿微博
 * version: 1.0.0
 */

public class WeiBoActivity3 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private UserTitle head;
    private SwipeRefreshLayout srl;
    RecyclerView rv;
    private View headView;
    private UserTitle headViewTab;
    private FooterView footerfView;
    private FrameLayout fl_head_back;
    private FrameLayout fl_head_gouser;
    private ImageView ci_head_avatar;
    private TextView tv_head_username;
    private TextView tv_head_user_follower;
    private TextView tv_head_user_follows;
    private TextView tv_head_user_intro;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo3);
        initView();
        initSwipeRefreshLayout();
        initHeadAndFootView();
        initRecyclerView();
    }

    private void initView() {
        head = (UserTitle) findViewById(R.id.activity_user_homepage_ut);
    }

    /**
     * 刷新的控件
     */
    private void initSwipeRefreshLayout() {
        //init SwipeRefreshLayout
        srl = (SwipeRefreshLayout) findViewById(R.id.activity_user_homepage_srl);
        srl.setColorSchemeResources(android.R.color.black);
        srl.setOnRefreshListener(this);
    }


    private void initHeadAndFootView() {
        headView = LayoutInflater.from(this).inflate(R.layout.item_user_headview, rv, false);

        headViewTab = new UserTitle(this);
        footerfView = new FooterView(this);
        fl_head_back = (FrameLayout) headView.findViewById(R.id.user_headview_back_fl);
        fl_head_back.setOnClickListener(this);
        fl_head_gouser = (FrameLayout) headView.findViewById(R.id.user_headview_go_user_setting);
        fl_head_gouser.setOnClickListener(this);
        ci_head_avatar = (ImageView) headView.findViewById(R.id.user_headview_image_head);
        tv_head_username = (TextView) headView.findViewById(R.id.user_headview_user_name);
        tv_head_user_follower = (TextView) headView.findViewById(R.id.user_headview_user_follower);
        tv_head_user_follows = (TextView) headView.findViewById(R.id.user_headview_user_follows);
        tv_head_user_intro = (TextView) headView.findViewById(R.id.user_headview_user_intro);
    }


    private void initRecyclerView() {
        rv = (RecyclerView) findViewById(R.id.activity_user_homepage_rv);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
       /* wrapperRecyclerAdapter = new DemoWrapperRecyclerAdapter(this);
        rv.setAdapter(wrapperRecyclerAdapter);
        recyclerViewLoadData = new RecyclerViewLoadData(layoutManager) {


            @Override
            public void loadMoreData(RecyclerView recyclerView) {
                if (!NetworkUtils.isConnected(getApplicationContext())) {
                    RecyclerViewStateUtils.setFooterViewState(recyclerView, FooterView.NetWorkError);
                    return;
                } else {
                    if (tabIsActivies && !activiesEnd) {
                        RecyclerViewStateUtils.setFooterViewState(recyclerView, FooterView.Loading);
                        loadDataMore();
                    } else {
                        if (tabIsSalt && !saltEnd) {
                            RecyclerViewStateUtils.setFooterViewState(recyclerView, FooterView.Loading);
                            loadDataMore2();
                        }
                    }
                }
            }

            @Override
            public void getFirstVisiblePosition(int firstVisiblePosition) {
                if (tabIsActivies) {
                    visibleItemActivities = firstVisiblePosition;
                    Log.i("visibleItemActive---->", visibleItemActivities + "");
                } else {
                    visibleItemSalt = firstVisiblePosition;
                    Log.i("visibleItemSalt---->", visibleItemSalt + "");
                }
            }

            @Override
            public void showTab() {
                head.setVisibility(View.VISIBLE);
            }

            @Override
            public void hideTab() {
                head.setVisibility(View.GONE);
            }
        };
        wrapperRecyclerAdapter.addHeaderView(headView);
        wrapperRecyclerAdapter.addHeaderView(headViewTab);
        wrapperRecyclerAdapter.addFooterView(footerView);
        rv.addOnScrollListener(recyclerViewLoadData);*/
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {

    }
}
