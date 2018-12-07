package com.example.mydemo.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Uid;

/**
 * author : 宋佳
 * time   : 2018/11/05
 * desc   :
 * version: 1.0.0
 */

@Entity

public class MyData2 {

    @Id
    private long id;

    private String userName;
    private int userAge;


    @Uid(2428186107625747761L)
    private String gender_new_now;
    // 必须有
    public MyData2(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getGender_new_now() {
        return gender_new_now;
    }

    public void setGender_new_now(String gender_new_now) {
        this.gender_new_now = gender_new_now;
    }
}
