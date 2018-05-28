package com.example.mydemo;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mydemo.fragment.MyListViewForToolbarHideFragment;
import com.example.mydemo.fragment.MyListViewFragment;
import com.example.mydemo.fragment.MyScrollViewFragment;
import com.example.mydemo.fragment.MyScrollerFragment;
import com.example.mydemo.fragment.ShowFragment;
import com.example.mydemo.fragment.ToolBarFragment;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FrameLayout fragment;

    private FragmentManager fragmentManager;
    private ShowFragment showFragment;
    private Toolbar tl_custom;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
        initView();
        Logger.d("debug");
        Logger.e("error");
        Logger.w("warning");
        Logger.v("verbose");
        Logger.i("information");
        Logger.wtf("What a Terrible Failure");
        Logger.json("{\n" +
                "\"employees\": [\n" +
                "{ \"firstName\":\"Bill\" , \"lastName\":\"Gates\" },\n" +
                "{ \"firstName\":\"George\" , \"lastName\":\"Bush\" },\n" +
                "{ \"firstName\":\"Thomas\" , \"lastName\":\"Carter\" }\n" +
                "]\n" +
                "}");
    }


    private void initView() {
        //京东RunningMan动画效果，和本次Toolbar无关


        tl_custom.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(tl_custom);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, tl_custom, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }


    private void initDrawer() {
        mDrawerLayout = ((DrawerLayout) findViewById(R.id.activity_drawer_drawerlayoutId));
        mNavigationView = ((NavigationView) findViewById(R.id.activity_drawer_nav));
        fragment = (FrameLayout) findViewById(R.id.fragment);

        tl_custom = (Toolbar) findViewById(R.id.tl_custom);
        //滚动条
        mNavigationView.getChildAt(0).setVerticalScrollBarEnabled(false);

        //nav menu item color
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setItemTextColor(getResources().getColorStateList(R.color.selector_nav_menu_item));
        mNavigationView.setItemTextAppearance(R.style.main_nav_menu_item_text_appearance);

        //click event
        mNavigationView.setNavigationItemSelectedListener(new CusNavigationItemStateListener());

        tl_custom.setTitle("自定义TextView");//设置Toolbar标题
        // 步骤1：获取FragmentManager
        fragmentManager = getFragmentManager();
        // 步骤2：获取FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ShowFragment showFragment = null;
        showFragment = ShowFragment.newInstance(R.layout.layout_measure);
        fragmentTransaction.replace(R.id.fragment, showFragment);
        fragmentTransaction.commit();

    }

    private class CusNavigationItemStateListener implements NavigationView.OnNavigationItemSelectedListener {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            onItemSelect(item);
            return false;
        }
    }

    private void onItemSelect(@NonNull MenuItem item) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.menu_drawer_item_measure:
                tl_custom.setTitle("自定义简单的测量");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_measure);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_textview:
                tl_custom.setTitle("自定义TextView");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_my_textview);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_progress:
                tl_custom.setTitle("自定义MyProgress");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_my_progress);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_music:
                tl_custom.setTitle("自定义音乐播放器");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_volume_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_mytoolbar:
                tl_custom.setTitle("自定义ToolBar");//设置Toolbar标题
                ToolBarFragment toolBarFragment = ToolBarFragment.newInstance(R.layout.layout_toolbar);
                fragmentTransaction.replace(R.id.fragment, toolBarFragment);
                break;
            case R.id.menu_drawer_item_myScrollView:
                //需要放到 activity中
                tl_custom.setTitle("自定义ScrollView");//设置Toolbar标题
                MyScrollViewFragment scrollViewFragment = MyScrollViewFragment.newInstance(R.layout.layout_my_scrollview);
                fragmentTransaction.replace(R.id.fragment, scrollViewFragment);
                break;
            case R.id.menu_drawer_item_myListView:
                //需要放到 activity中
                tl_custom.setTitle("自定义ListView");//设置Toolbar标题
                MyListViewFragment listViewFragment = MyListViewFragment.newInstance(R.layout.layout_my_listview);
                fragmentTransaction.replace(R.id.fragment, listViewFragment);
                break;
            case R.id.menu_drawer_item_myToolbarHideListView:
                tl_custom.setTitle("自定义带有Toolbar的ListView");//设置Toolbar标题
                MyListViewForToolbarHideFragment myListViewForToolbarHideFragment = MyListViewForToolbarHideFragment.newInstance(R.layout.layout_my_listview_for_toolbar_hide);
                fragmentTransaction.replace(R.id.fragment, myListViewForToolbarHideFragment);
                break;
            case R.id.menu_drawer_item_move:
                tl_custom.setTitle("自定义跟着手势运动的View");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_move_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_move_for_layoutparams:
                tl_custom.setTitle("自定义跟着手势运动的View");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_move_view_for_layoutparmas);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_scroller:
                tl_custom.setTitle("scroller使用");//设置Toolbar标题
                MyScrollerFragment  myScrollerFragment= MyScrollerFragment.newInstance(R.layout.layout_move_view_for_layoutparmas);
                fragmentTransaction.replace(R.id.fragment, myScrollerFragment);
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawers();
        fragmentTransaction.commit();
    }


}