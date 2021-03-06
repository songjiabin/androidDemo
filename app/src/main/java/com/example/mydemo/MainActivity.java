package com.example.mydemo;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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

import com.example.mydemo.activity.GetPhoneNumberActivity;
import com.example.mydemo.activity.MyViewGroupActivity;
import com.example.mydemo.activity.ObjectBoxActivity;
import com.example.mydemo.activity.OkHttpActivity;
import com.example.mydemo.activity.OtherRecyclerProgressActivity;
import com.example.mydemo.activity.OtherRecyclerViewActivity;
import com.example.mydemo.activity.PopularWindowActivity;
import com.example.mydemo.activity.QQSkidActivity;
import com.example.mydemo.activity.RemoveViewActivity;
import com.example.mydemo.activity.RxJavaDemoActivity;
import com.example.mydemo.activity.ScrollerActivity;
import com.example.mydemo.activity.ServiceActivity;
import com.example.mydemo.activity.ToolBarActivity;
import com.example.mydemo.event.EventFragment;
import com.example.mydemo.fragment.AndroidToJsFragment;
import com.example.mydemo.fragment.BankPickerFragment;
import com.example.mydemo.fragment.EvaluationNegReasonFragment;
import com.example.mydemo.fragment.EvaluationRatingFragment;
import com.example.mydemo.fragment.JsToAndroidFragment;
import com.example.mydemo.fragment.JsToAndroidFragment2;
import com.example.mydemo.fragment.JsToAndroidFragment3;
import com.example.mydemo.fragment.MarqueeFragment;
import com.example.mydemo.fragment.MyAnimationDemoFragment;
import com.example.mydemo.fragment.MyListViewForToolbarHideFragment;
import com.example.mydemo.fragment.MyListViewFragment;
import com.example.mydemo.fragment.MyScrollViewFragment;
import com.example.mydemo.fragment.MyScrollerFragment;
import com.example.mydemo.fragment.ObjectAnimatorFragment;
import com.example.mydemo.fragment.ObjectAnimatorFragment2;
import com.example.mydemo.fragment.ObjectAnimatorFragment3;
import com.example.mydemo.fragment.ShowFragment;
import com.example.mydemo.fragment.SmileFragment;
import com.example.mydemo.fragment.ToolBarFragment;
import com.example.mydemo.fragment.ValueAnimatorFragment;
import com.example.mydemo.fragment.ViewAnimationUtilsFragment;
import com.example.mydemo.fragment.WebViewFragment;
import com.example.mydemo.fragment.WeiboFragment;
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
        showFragment = ShowFragment.newInstance(R.layout.layout_my_progress);
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
                tl_custom.setTitle("ViewGroup");//设置Toolbar标题
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
            case R.id.menu_drawer_item_marquee:
                tl_custom.setTitle("跑马灯");//设置Toolbar标题
                MarqueeFragment marqueeFragment = MarqueeFragment.newInstance(R.layout.fragment_marquee);
                fragmentTransaction.replace(R.id.fragment, marqueeFragment);
                break;
            case R.id.menu_drawer_item_start:
                tl_custom.setTitle("评分系统");//设置Toolbar标题
                EvaluationRatingFragment evaluationRatingFragment = EvaluationRatingFragment.newInstance(R.layout.fragment_marquee);
                fragmentTransaction.replace(R.id.fragment, evaluationRatingFragment);
                break;
            case R.id.menu_drawer_item_chaping:
                tl_custom.setTitle("评分系统");//设置Toolbar标题
                EvaluationNegReasonFragment evaluationNegReasonFragment = EvaluationNegReasonFragment.newInstance(R.layout.fragment_marquee);
                fragmentTransaction.replace(R.id.fragment, evaluationNegReasonFragment);
                break;
            case R.id.menu_drawer_item_yinhangxuanzeqi:
                tl_custom.setTitle("银行卡选择");//设置Toolbar标题
                BankPickerFragment bankPickerFragment = BankPickerFragment.newInstance(R.layout.layout_bank_picker);
                fragmentTransaction.replace(R.id.fragment, bankPickerFragment);
                break;
            case R.id.menu_drawer_item_weibozhuye:
                tl_custom.setTitle("仿照微博主页");//设置Toolbar标题
                WeiboFragment weiboFragment = WeiboFragment.newInstance(R.layout.layout_bank_picker);
                fragmentTransaction.replace(R.id.fragment, weiboFragment);
                break;
            case R.id.menu_drawer_item_ViewAnimationUtils:
                tl_custom.setTitle("ViewAnimationUtils");
                ViewAnimationUtilsFragment viewAnimationUtilsFragment = ViewAnimationUtilsFragment.newInstance(R.layout.fragment_viewanimation_utils);
                fragmentTransaction.replace(R.id.fragment, viewAnimationUtilsFragment);
                break;
            case R.id.menu_drawer_item_smileView:
                tl_custom.setTitle("哭脸笑脸");
                SmileFragment smileFragment = SmileFragment.newInstance(R.layout.fragment_smile);
                fragmentTransaction.replace(R.id.fragment, smileFragment);
                break;
            case R.id.menu_drawer_item_loading_view:
                tl_custom.setTitle("自定义LoadingView");
                showFragment = ShowFragment.newInstance(R.layout.fragment_loading_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;

            case R.id.menu_drawer_item_loading_view2:
                tl_custom.setTitle("自定义LoadingView2");
                showFragment = ShowFragment.newInstance(R.layout.fragment_loading_view2);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;

            case R.id.menu_drawer_item_radar:
                tl_custom.setTitle("自定义雷达");
                showFragment = ShowFragment.newInstance(R.layout.layout_radar_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;

            case R.id.menu_drawer_item_turnable:
                tl_custom.setTitle("自定义转盘");
                showFragment = ShowFragment.newInstance(R.layout.layout_turntable);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;

            case R.id.menu_drawer_item_path:
                tl_custom.setTitle("自定义path学习");
                showFragment = ShowFragment.newInstance(R.layout.layout_path_view);
                fragmentTransaction.replace(R.id.fragment, showFragment);
                break;

            case R.id.menu_drawer_item_toolBar:
                tl_custom.setTitle("自定义ToolBar学习");
                Intent intent = new Intent(this, ToolBarActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_drawer_item_popular:
                tl_custom.setTitle("自定义popular使用（单选和多选的实现）");
                Intent intentPopular = new Intent(this, PopularWindowActivity.class);
                startActivity(intentPopular);
                break;
            case R.id.menu_drawer_item_recyview:
                tl_custom.setTitle("other recyclerView学习");
                Intent otherRecyclerViewIntent = new Intent(this, OtherRecyclerViewActivity.class);
                startActivity(otherRecyclerViewIntent);
                break;
            case R.id.menu_drawer_item_recyview_progress:
                tl_custom.setTitle("recyclerView_progress 复用");
                Intent otherRecyclerViewProgressIntent = new Intent(this, OtherRecyclerProgressActivity.class);
                startActivity(otherRecyclerViewProgressIntent);
                break;
            case R.id.menu_drawer_item_remove_view:
                tl_custom.setTitle("View的滑动方式");
                Intent removeViewIntent = new Intent(this, RemoveViewActivity.class);
                startActivity(removeViewIntent);
                break;
            case R.id.menu_drawer_item_scroller2:
                tl_custom.setTitle("View的滑动方式");
                Intent srollViewIntent = new Intent(this, ScrollerActivity.class);
                startActivity(srollViewIntent);
                break;

            case R.id.menu_drawer_scroller_qq:
                tl_custom.setTitle("仿照qq侧滑");
                Intent intent1 = new Intent(this, QQSkidActivity.class);
                startActivity(intent1);

                break;

            case R.id.menu_drawer_viewGroup:
                tl_custom.setTitle("ViewGroup");
                Intent intent_viewGroup = new Intent(this, MyViewGroupActivity.class);
                startActivity(intent_viewGroup);

                break;

            case R.id.menu_drawer_okhttp:
                tl_custom.setTitle("ViewGroup");
                Intent intent_okhttp = new Intent(this, OkHttpActivity.class);
                startActivity(intent_okhttp);

                break;
            case R.id.menu_drawer_get_phone_number:
                tl_custom.setTitle("获取本机号码");
                Intent intent_get_phone_number = new Intent(this, GetPhoneNumberActivity.class);
                startActivity(intent_get_phone_number);
            case R.id.menu_drawer_start_service:
                tl_custom.setTitle("Service");
                Intent intent_service = new Intent(this, ServiceActivity.class);
                startActivity(intent_service);
                break;
            case R.id.menu_drawer_start_rxjava:
                tl_custom.setTitle("rxjava");
                Intent intent_rxjava = new Intent(this, RxJavaDemoActivity.class);
                startActivity(intent_rxjava);
                break;
            case R.id.menu_drawer_start_objbox:
                tl_custom.setTitle("objectBox");
                Intent intent_objectBox = new Intent(this, ObjectBoxActivity.class);
                startActivity(intent_objectBox);
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