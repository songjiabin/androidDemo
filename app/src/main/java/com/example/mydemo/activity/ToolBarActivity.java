package com.example.mydemo.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/08/10
 * desc   :
 * version: 1.0.0
 */

public class ToolBarActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private Toast mToast;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // 主标题,默认为app_label的名字  就是左边的那个标题
        mToolbar.setTitle("Title22222");
        mToolbar.setTitleTextColor(Color.YELLOW);

        // 副标题
        mToolbar.setSubtitle("Sub title");
        mToolbar.setSubtitleTextColor(Color.parseColor("#80ff0000"));


        //侧边栏的按钮  比如返回的那个
        mToolbar.setNavigationIcon(android.R.drawable.checkbox_on_background);
        //取代原本的actionbar
        setSupportActionBar(mToolbar);


        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);

        //设置NavigationIcon的点击事件,需要放在setSupportActionBar之后设置才会生效
        //因为setSupportActionBar里面也会setNavigationOnClickListener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.setText("click NavigationIcon");
                mToast.show();
            }
        });


        //设置toolBar上的MenuItem点击事件
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.test_menu1:
                        mToast.setText("click edit");
                        break;
                    case R.id.test_menu2:
                        mToast.setText("click share");
                        break;
                    case R.id.test_menu3:
                        //弹出自定义overflow
                        popUpMyOverflow();
                        return true;
                }
                mToast.show();
                return true;
            }
        });

    }


    /**
     * 弹出自定义的popWindow
     */
    public void popUpMyOverflow() {
        //获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        //状态栏高度+toolbar的高度
        int yOffset = frame.top + mToolbar.getHeight();
        if (null == mPopupWindow) {
            //初始化PopupWindow的布局
            View popView = getLayoutInflater().inflate(R.layout.popular_window, null);
            //popView即popupWindow的布局，ture设置focusAble.
            mPopupWindow = new PopupWindow(popView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            //点击外部关闭。
            mPopupWindow.setOutsideTouchable(true);
            //设置一个动画。
            mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            //设置Gravity，让它显示在右上角。
            mPopupWindow.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
            //设置item的点击监听

        } else {
            mPopupWindow.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_toobar, menu);//加载menu布局
        return true;
    }
}
