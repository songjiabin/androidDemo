package com.example.mydemo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.bean.BankModel;

import java.util.List;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.bankpickerview.BankPopupWindow
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/01/18
 *     desc   :
 * </pre>
 */

public class BankPopupWindow implements View.OnKeyListener {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private OnBankSelectListener mListener;
    private View mView;
    private BankPickerView mPickerView;
    private BankModel mBankModel;
    private List<BankModel> mData;

    public BankPopupWindow(Activity activity) {
        this.mContext = activity;
        initPopupWindow();
    }

    private void initPopupWindow() {
        mView = View.inflate(mContext, R.layout.bank_popup_view, null);
        mView.setFocusable(true);
        mView.setFocusableInTouchMode(true);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        TextView tvCancel = (TextView) mView.findViewById(R.id.tv_bank_cancel);
        TextView tvConfirm = (TextView) mView.findViewById(R.id.tv_bank_confirm);
        mPickerView = (BankPickerView) mView.findViewById(R.id.pv_bank);
        mPickerView.setOffset(2);
        mPickerView.setOnBankSelectedListener(new BankPickerView.OnBankSelectedListener() {
            @Override
            public void onSelected(int selectedIndex, BankModel item) {
                mBankModel = item;
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null) {
                    if (mBankModel == null) {
                        if (mData != null && mData.size() > 0) {
                            mListener.onBankSelect(mData.get(0));
                        }
                    } else {
                        mListener.onBankSelect(mBankModel);
                    }
                }
            }
        });
    }

    public void show() {
        if (mPopupWindow != null) {
            mPopupWindow.setAnimationStyle(R.style.BankAnimalStyle);
            mPopupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
        }
    }

    private void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void setData(List<BankModel> data) {
        if (data != null && mPickerView != null) {
            mData = data;
            if (mPickerView.getData() == null || mPickerView.getData().size() <= 0) {
                mPickerView.setData(data);
            }
        }
    }

    public void setCurPosition(BankModel model) {
        if (model == null || mData == null || mPickerView == null) {
            return;
        }
        this.mBankModel = model;
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(model.bankName, mData.get(i).bankName)) {
                mPickerView.setSelection(i);
            }
        }
    }






    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
            return true;
        }
        return false;
    }

    public void setOnBankSelectListener(OnBankSelectListener listener) {
        this.mListener = listener;
    }

    public interface OnBankSelectListener {
        void onBankSelect(BankModel model);
    }

}