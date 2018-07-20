package com.example.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mydemo.bean.BankModel;
import com.example.mydemo.bean.BankModelInfo;
import com.example.mydemo.utils.GsonUtil;
import com.example.mydemo.view.BankPickerView;

import java.io.IOException;
import java.io.InputStream;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bank_picker);


        mPickerView = (BankPickerView) findViewById(R.id.bank_picker_view);
        mBankName1 = (TextView) findViewById(R.id.tv_bank_name);
        initData();
        initPickerView();
        mPickerView.setOnBankSelectedListener(new BankPickerView.OnBankSelectedListener() {
            @Override
            public void onSelected(int selectedIndex, BankModel item) {
                if (item == null) return;
                mBankName1.setText(item.bankName);
            }
        });


    }


    private void initData() {
        try {
            InputStream is = getAssets().open("bankInfo.json");
            int length = is.available();
            byte[] bytes = new byte[length];
            is.read(bytes);
            String result = new String(bytes, "utf-8");

            BankModelInfo bankModelInfo = GsonUtil.GsonToBean(result, BankModelInfo.class);
            mData = bankModelInfo.getData();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initPickerView() {
        //设置 第一个和最后一个的空白
        mPickerView.setOffset(2);
        mPickerView.setData(mData);

    }

}
