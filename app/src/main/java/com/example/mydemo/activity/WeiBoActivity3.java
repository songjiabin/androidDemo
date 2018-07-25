package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.bean.UserNickname;
import com.example.mydemo.bean.UserNumber;
import com.example.mydemo.recyclerview.DemoWrapperRecyclerAdapter;
import com.example.mydemo.recyclerview.RecyclerViewLoadData;
import com.example.mydemo.utils.NetworkUtils;
import com.example.mydemo.utils.RecyclerViewStateUtils;
import com.example.mydemo.view.FooterView;
import com.example.mydemo.view.UserTitle;

import java.util.ArrayList;

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

    private FrameLayout fl_head_back;
    private FrameLayout fl_head_gouser;
    private ImageView ci_head_avatar;
    private TextView tv_head_username;
    private TextView tv_head_user_follower;
    private TextView tv_head_user_follows;
    private TextView tv_head_user_intro;
    private LinearLayoutManager layoutManager;
    private DemoWrapperRecyclerAdapter wrapperRecyclerAdapter;
    private RecyclerViewLoadData recyclerViewLoadData;

    private boolean tabIsActivies = true;
    private boolean tabIsSalt = false;
    private boolean activiesEnd = false;
    private boolean saltEnd = false;

    private int visibleItemActivities;
    private int visibleItemSalt;
    FooterView footerView;

    ArrayList<UserNickname> mListActivities = new ArrayList<>();
    ArrayList<UserNumber> mListSalt = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo3);
        initView();
        initSwipeRefreshLayout();
        initHeadAndFootView();
        initRecyclerView();
        loadTabListener();
        loadData();
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
        footerView = new FooterView(this);
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
        wrapperRecyclerAdapter = new DemoWrapperRecyclerAdapter(this);
        rv.setAdapter(wrapperRecyclerAdapter);

        recyclerViewLoadData = new RecyclerViewLoadData(layoutManager){

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
        rv.addOnScrollListener(recyclerViewLoadData);

    }


    @Override
    public void onRefresh() {
        srl.setRefreshing(true);
        if (tabIsActivies) {
            loadData();
        } else {
            loadData2();
        }
    }

    @Override
    public void onClick(View v) {

    }



    private void loadDataMore() {
        RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
        for (int i = 0; i < 10; i++) {
            UserNickname userNickname = new UserNickname();
            userNickname.uname = i + "more";
            mListActivities.add(userNickname);
        }
        if (tabIsActivies) {
            wrapperRecyclerAdapter.setList(mListActivities);
            if (mListActivities.size() < 10) {
                RecyclerViewStateUtils.setFooterViewState(rv, FooterView.TheEnd);
                activiesEnd = true;
            }
        }
    }

    private void loadDataMore2() {
        RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
        for (int i = 0; i < 10; i++) {
            UserNumber userNumber = new UserNumber();
            userNumber.follows = i + "more";
            mListSalt.add(userNumber);
        }
        if (tabIsSalt) {
            wrapperRecyclerAdapter.setList(mListSalt);
            if (mListSalt.size() < 10) {
                RecyclerViewStateUtils.setFooterViewState(rv, FooterView.TheEnd);
                saltEnd = true;
            }
        }
    }



    private void loadTabListener() {
        headViewTab.setOnTitleClickListener(new UserTitle.OnTitleClickListener() {
            @Override
            public void onClickActivies(View view) {
                rv.stopScroll();
                tabIsActivies = true;
                tabIsSalt = false;
                visibleItemActivities = 0;
                //head与headViewTab统一状态
                head.setTabActivity();
                RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
                if (mListActivities.size() == 0) {
                    RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Loading);
                    loadData();
                } else {
                    wrapperRecyclerAdapter.setList(mListActivities);
                }
                if (activiesEnd) {
                    RecyclerViewStateUtils.setFooterViewState(rv, FooterView.TheEnd);
                } else {
                    RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
                }
            }

            @Override
            public void onClickSalt(View view) {
                rv.stopScroll();
                tabIsSalt = true;
                tabIsActivies = false;
                visibleItemSalt = 0;
                head.setTabSalt();
                RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
                if (mListSalt.size() == 0) {
                    RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Loading);
                    loadData2();
                } else {
                    wrapperRecyclerAdapter.setList(mListSalt);
                }
                if (saltEnd) {
                    RecyclerViewStateUtils.setFooterViewState(rv, FooterView.TheEnd);
                } else {
                    RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
                }
            }
        });


        head.setOnTitleClickListener(new UserTitle.OnTitleClickListener() {
            @Override
            public void onClickActivies(View view) {
                rv.stopScroll();
                tabIsActivies = true;
                tabIsSalt = false;
                headViewTab.setTabActivity();
                RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
                if (mListActivities.size() == 0) {
                    RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Loading);
                    layoutManager.scrollToPositionWithOffset(1, 0);
                    loadData();
                } else {
                    wrapperRecyclerAdapter.setList(mListActivities);
                    if (visibleItemActivities == 0) {
                        layoutManager.scrollToPositionWithOffset(1, 0);
                    } else {
                        layoutManager.scrollToPositionWithOffset(visibleItemActivities, 0);
                        Log.i("MyvisibleItem1---->", visibleItemActivities + "");
                    }
                    if (activiesEnd) {
                        RecyclerViewStateUtils.setFooterViewState(rv, FooterView.TheEnd);
                    } else {
                        RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
                    }
                }
            }

            @Override
            public void onClickSalt(View view) {
                rv.stopScroll();
                tabIsActivies = false;
                tabIsSalt = true;
                headViewTab.setTabSalt();
                RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
                if (mListSalt.size() == 0) {
                    RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Loading);
                    layoutManager.scrollToPositionWithOffset(1, 0);
                    loadData2();
                } else {
                    wrapperRecyclerAdapter.setList(mListSalt);
                    if (visibleItemSalt == 0) {
                        layoutManager.scrollToPositionWithOffset(1, 0);
                    } else {
                        layoutManager.scrollToPositionWithOffset(visibleItemSalt, 0);
                        Log.i("MyvisibleItem2---->", visibleItemSalt + "");
                    }
                    if (saltEnd) {
                        RecyclerViewStateUtils.setFooterViewState(rv, FooterView.TheEnd);
                    } else {
                        RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
                    }
                }
            }
        });
    }

    private void loadData() {
        RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
        mListActivities.clear();
        for (int i = 0; i < 10; i++) {
            UserNickname userNickname = new UserNickname();
            userNickname.uname = i + "";
            mListActivities.add(userNickname);
        }
        if (tabIsActivies) {
            activiesEnd = false;
            wrapperRecyclerAdapter.setList(mListActivities);
            if (mListActivities.size() < 10) {
                RecyclerViewStateUtils.setFooterViewState(rv, FooterView.TheEnd);
                activiesEnd = true;
            }
        }
        srl.setRefreshing(false);


    }
    private void loadData2() {
        RecyclerViewStateUtils.setFooterViewState(rv, FooterView.Normal);
        mListSalt.clear();
        for (int i = 0; i < 10; i++) {
            UserNumber userNumber = new UserNumber();
            userNumber.follows = i + "";
            mListSalt.add(userNumber);
        }
        if (tabIsSalt) {
            saltEnd = false;
            wrapperRecyclerAdapter.setList(mListSalt);
            if (mListSalt.size() < 10) {
                RecyclerViewStateUtils.setFooterViewState(rv, FooterView.TheEnd);
                saltEnd = true;
            }
        }
        srl.setRefreshing(false);


    }

}
