package com.wyman.kotlinapplication.service

import com.wyman.kotlinapplication.entity.Banner
import com.wyman.kotlinapplication.entity.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by chenxz on 2018/4/21.
 */
interface ApiService {

    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    fun getBanners(): Observable<HttpResult<List<Banner>>>
}