package com.example.mydemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.mydemo.bean.BankModel;
import com.example.mydemo.view.BankPickerView;

import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class DemoActivity extends AppCompatActivity {


    private BankPickerView mPickerView;
    private List<BankModel> mData;
    private TextView mBankName1;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);


        view = (View) findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("宋佳宾", "OnTouchListener");

                return false;

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("宋佳宾", "OnClickListener");
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    //当系统配置发生变化的时候 次acticty销毁的时候 会调用此方法
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);


    }


    //当系统配置发生变化的时候 次acticty销毁的时候 然后重新创建的时候调用次方法
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }




    /* @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                int x = (int) event.getX();
                int y = (int) event.getY();
                int rawx = (int) event.getRawX();
                int rawy = (int) event.getRawY();


                Log.i("宋佳宾", "x--->" + x);
                Log.i("宋佳宾", "y--->" + y);
                Log.i("宋佳宾", "rawx--->" + rawx);
                Log.i("宋佳宾", "rawy--->" + rawy);


                break;

            default:
                break;
        }


        return true;
    }*/


}
