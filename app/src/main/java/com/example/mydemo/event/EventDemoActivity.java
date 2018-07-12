package com.example.mydemo.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydemo.R;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * author : 宋佳
 * time   : 2018/07/12
 * desc   :
 * version: 1.0.0
 */

public class EventDemoActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private ArrayList<View> mViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_event);
        initViews();
        initData(true);
    }

    protected void initViews() {

        mViewPager = (ViewPager) findViewById(R.id.myViewPager);
        mViews = new ArrayList<>();
    }

    protected void initData(final boolean isListView) {
        //初始化mViews列表
        Flowable.just("view1", "view2", "view3", "view4").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //当前View
                View view;
                if (isListView) {
                    //初始化ListView
                    ListView listView = new ListView(EventDemoActivity.this);
                    final ArrayList<String> datas = new ArrayList<>();
                    //初始化数据，datas ,data0 ...data49
                    Flowable.range(0, 50).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            datas.add("data" + integer);
                        }
                    });
                    //初始化adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<>
                            (EventDemoActivity.this, android.R.layout.simple_list_item_1, datas);
                    //设置adapter
                    listView.setAdapter(adapter);
                    //将ListView赋值给当前View
                    view = listView;
                } else {
                    //初始化TextView
                    TextView textView = new TextView(EventDemoActivity.this);
                    textView.setClickable(true);
                    textView.setGravity(Gravity.CENTER);
                    textView.setText(s);
                    //将TextView赋值给当前View
                    view = textView;
                }
                //将当前View添加到ViewPager的ViewList中去
                mViews.add(view);
            }
        });
        //设置ViewPager的Adapter
        mViewPager.setAdapter(adapter);












    }

    PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));

            return mViews.get(position);
        }

    };

}
