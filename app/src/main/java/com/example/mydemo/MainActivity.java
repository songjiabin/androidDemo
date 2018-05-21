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

import com.example.mydemo.fragment.ShowFragment;
import com.example.mydemo.fragment.ToolBarFragment;

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
        showFragment = ShowFragment.newInstance(R.layout.layout_my_textview);
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
            case R.id.menu_drawer_item_mytoolbar:
                tl_custom.setTitle("自定义ToolBar");//设置Toolbar标题
                ToolBarFragment toolBarFragment = ToolBarFragment.newInstance(R.layout.layout_toolbar);
                fragmentTransaction.replace(R.id.fragment, toolBarFragment);
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawers();
        fragmentTransaction.commit();
    }


}