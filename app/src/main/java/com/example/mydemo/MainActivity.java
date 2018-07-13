package com.example.mydemo;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mydemo.event.EventFragment;
import com.example.mydemo.fragment.AndroidToJsFragment;
import com.example.mydemo.fragment.JsToAndroidFragment;
import com.example.mydemo.fragment.JsToAndroidFragment2;
import com.example.mydemo.fragment.JsToAndroidFragment3;
import com.example.mydemo.fragment.MyAnimationDemoFragment;
import com.example.mydemo.fragment.MyListViewForToolbarHideFragment;
import com.example.mydemo.fragment.MyListViewFragment;
import com.example.mydemo.fragment.MyScrollViewFragment;
import com.example.mydemo.fragment.MyScrollerFragment;
import com.example.mydemo.fragment.ObjectAnimatorFragment;
import com.example.mydemo.fragment.ObjectAnimatorFragment2;
import com.example.mydemo.fragment.ObjectAnimatorFragment3;
import com.example.mydemo.fragment.ShowFragment;
import com.example.mydemo.fragment.ToolBarFragment;
import com.example.mydemo.fragment.ValueAnimatorFragment;
import com.example.mydemo.fragment.WebViewFragment;
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
                MyScrollerFragment myScrollerFragment = MyScrollerFragment.newInstance(R.layout.layout_move_view_for_layoutparmas);
                fragmentTransaction.replace(R.id.fragment, myScrollerFragment);
                break;
            case R.id.menu_drawer_item_viewDragHelper:
                tl_custom.setTitle("ViewDragHelper的使用");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_drag_help_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_shape:
                tl_custom.setTitle("shape AND selector 使用");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_shape_demo);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_clock:
                tl_custom.setTitle("自定义闹钟的使用");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_myclock_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_PorterDuffXfermode:
                tl_custom.setTitle("PorterDuffXfermode的使用");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_porter_duffx_fermode_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_PorterDuffXfermode2:
                tl_custom.setTitle("刮刮乐");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_porter_duffx_fermode_view2);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_surfaceView:
                tl_custom.setTitle("SurfaceView");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_my_surfaceview);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_surfaceView2:
                tl_custom.setTitle("SurfaceView2");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_my_surfaceview2);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_view_animation:
                tl_custom.setTitle("视图动画");//设置Toolbar标题
                MyAnimationDemoFragment myAnimationDemoFragment = MyAnimationDemoFragment.newInstance(R.layout.layout_my_animation);
                fragmentTransaction.replace(R.id.fragment, myAnimationDemoFragment);
                break;
            case R.id.menu_drawer_item_myViewGroup2:
                tl_custom.setTitle("ViewGropu");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_my_viewgroup2);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_animation:
                tl_custom.setTitle("属性动画");//设置Toolbar标题
                ValueAnimatorFragment valueAnimatorFragment = ValueAnimatorFragment.newInstance(R.layout.fragment_value_animator);
                fragmentTransaction.replace(R.id.fragment, valueAnimatorFragment);
                break;
            case R.id.menu_drawer_item_animation_demo:
                tl_custom.setTitle("属性动画_value_animation");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_animation_demo);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_objectAnimator_demo:
                tl_custom.setTitle("属性动画_ObjectAnimator");//设置Toolbar标题
                ObjectAnimatorFragment valueAnimatorFragment1 = ObjectAnimatorFragment.newInstance(R.layout.fragment_value_animator);
                fragmentTransaction.replace(R.id.fragment, valueAnimatorFragment1);
                break;
            case R.id.menu_drawer_item_objectAnimator_demo2:
                tl_custom.setTitle("属性动画_ObjectAnimator2");//设置Toolbar标题
                ObjectAnimatorFragment2 objectAnimatorFragment = ObjectAnimatorFragment2.newInstance(R.layout.layout_object_animator);
                fragmentTransaction.replace(R.id.fragment, objectAnimatorFragment);
                break;
            case R.id.menu_drawer_item_objectAnimator_demo3:
                tl_custom.setTitle("属性动画_ObjectAnimator3");//设置Toolbar标题
                ObjectAnimatorFragment3 objectAnimatorFragment3 = ObjectAnimatorFragment3.newInstance(R.layout.layout_object_animator3);
                fragmentTransaction.replace(R.id.fragment, objectAnimatorFragment3);
                break;
            case R.id.menu_drawer_item_myEditText:
                tl_custom.setTitle("自定义EditText");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_my_editext);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_myEditText2:
                tl_custom.setTitle("自定义EditText2");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_my_edittext2);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_my_searchView:
                tl_custom.setTitle("带有搜索历史的EditText");//设置Toolbar标题
                showFragment = ShowFragment.newInstance(R.layout.layout_search_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;
            case R.id.menu_drawer_item_android_js:
                tl_custom.setTitle("android 调用 JS 代码");//设置Toolbar标题
                AndroidToJsFragment androidToJsFragment = AndroidToJsFragment.newInstance(R.layout.layout_android_js);
                fragmentTransaction.replace(R.id.fragment, androidToJsFragment);
                break;
            case R.id.menu_drawer_item_android_js2:
                tl_custom.setTitle("JS 调用 android 代码1");//设置Toolbar标题
                JsToAndroidFragment jsToAndroidFragment = JsToAndroidFragment.newInstance(R.layout.layout_android_js2);
                fragmentTransaction.replace(R.id.fragment, jsToAndroidFragment);
                break;
            case R.id.menu_drawer_item_android_js3:
                tl_custom.setTitle("JS 调用 android 代码2");//设置Toolbar标题
                JsToAndroidFragment2 jsToAndroidFragment2 = JsToAndroidFragment2.newInstance(R.layout.layout_android_js2);
                fragmentTransaction.replace(R.id.fragment, jsToAndroidFragment2);
                break;
            case R.id.menu_drawer_item_android_js4:
                tl_custom.setTitle("JS 调用 android 代码3");//设置Toolbar标题
                JsToAndroidFragment3 jsToAndroidFragment3 = JsToAndroidFragment3.newInstance(R.layout.layout_android_js3);
                fragmentTransaction.replace(R.id.fragment, jsToAndroidFragment3);
                break;
            case R.id.menu_drawer_item_webview:
                tl_custom.setTitle("webView");//设置Toolbar标题
                WebViewFragment webViewFragment = WebViewFragment.newInstance(R.layout.fragment_webview);
                fragmentTransaction.replace(R.id.fragment, webViewFragment);
                break;
            case R.id.menu_drawer_item_event_1:
                tl_custom.setTitle("事件分发机制");//设置Toolbar标题
                EventFragment eventFragment = EventFragment.newInstance(R.layout.layout_fragment_event);
                fragmentTransaction.replace(R.id.fragment, eventFragment);
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawers();
        fragmentTransaction.commit();
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}