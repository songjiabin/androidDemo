package com.example.mydemo.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/07/19
 * desc   :
 * version: 1.0.0
 */

public class MyDialog {

    private Context mContext;
    private AlertDialog mAlertDialog;

    public MyDialog(Context context) {
        mContext = context;
        mAlertDialog = new AlertDialog.Builder(context).create();
        if (mAlertDialog.getWindow() != null) {
            mAlertDialog.getWindow().setDimAmount(0);
        }

        View view = View.inflate(mContext, R.layout.layout_evaluation_rationbar, null);
        mAlertDialog.setView(view);
        mAlertDialog.setCanceledOnTouchOutside(false);
    }


    public void show() {
        if (mAlertDialog != null && !mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
    }

    public void dismiss() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }
}
