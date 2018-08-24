package com.example.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mydemo.bean.BankModel;
import com.example.mydemo.view.BankPickerView;
import com.orhanobut.logger.Logger;

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

    private RelativeLayout viewGroup;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_demo);

        viewGroup = (RelativeLayout) findViewById(R.id.viewGroup);

        view = (View) findViewById(R.id.view);

        view.setTranslationX(10f);


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int left = view.getLeft();
        int x = (int) view.getX();




        Logger.d("left--》", left, "X--》" + x);
    }
}
