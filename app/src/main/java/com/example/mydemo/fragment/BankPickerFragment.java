package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.bean.BankModel;
import com.example.mydemo.bean.BankModelInfo;
import com.example.mydemo.utils.GsonUtil;
import com.example.mydemo.view.BankPickerView;
import com.example.mydemo.view.BankPopupWindow;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * author : 宋佳
 * time   : 2018/07/20
 * desc   :
 * version: 1.0.0
 */

public class BankPickerFragment extends Fragment {


    @LayoutRes
    int sampleLayoutRes;
    private BankPickerView mPickerView;
    private TextView mBankName1;
    private ArrayList<BankModel> mData;
    private TextView mBankName2;
    private View view;

    public static BankPickerFragment newInstance(@LayoutRes int sampleLayoutRes) {
        BankPickerFragment fragment = new BankPickerFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bank_picker, container, false);
        this.view=view;

        mPickerView = (BankPickerView) view.findViewById(R.id.bank_picker_view);
        mBankName1 = (TextView) view.findViewById(R.id.tv_bank_name);
        mBankName2 = (TextView) view.findViewById(R.id.tv_bank_name1);
        initData();
        initPickerView();
        mPickerView.setOnBankSelectedListener(new BankPickerView.OnBankSelectedListener() {
            @Override
            public void onSelected(int selectedIndex, BankModel item) {
                if (item == null) return;
                mBankName1.setText(item.bankName);
            }
        });


        initPopupWindow();



        return view;
    }

    private void initPopupWindow() {
        final BankPopupWindow popupWindow = new BankPopupWindow(getActivity());
        popupWindow.setOnBankSelectListener(new BankPopupWindow.OnBankSelectListener() {
            @Override
            public void onBankSelect(BankModel model) {
                if (model == null) return;
                mBankName2.setText(model.bankName);
            }
        });
        view.findViewById(R.id.rl_bank_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.show();
                popupWindow.setData(mData);
            }
        });
    }

    private void initPickerView() {
        //设置 第一个和最后一个的空白
        mPickerView.setOffset(2);
        mPickerView.setData(mData);
    }

    private void initData() {
        try {
            InputStream is = getActivity().getAssets().open("bankInfo.json");
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes");

        }
    }


}
