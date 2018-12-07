package com.example.mydemo.rxjava;

import com.example.mydemo.bean.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author : 宋佳
 * time   : 2018/11/02
 * desc   :
 * version: 1.0.0
 */

public interface ApiService {

    //请求网络的接口
    @GET("top250")
    Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);

}
