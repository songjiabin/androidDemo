package com.example.mydemo.bean;

import java.util.ArrayList;

/**
 * author : 宋佳
 * time   : 2018/07/20
 * desc   :
 * version: 1.0.0
 */

public class BankModelInfo {


    public String status;
    public String desc;

    public ArrayList<BankModel> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<BankModel> getData() {
        return data;
    }

    public void setData(ArrayList<BankModel> data) {
        this.data = data;
    }
}
