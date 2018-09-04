package com.wyman.kotlinapplication

import android.content.Context
import com.wyman.kotlinapplication.entity.Banner
import com.wyman.kotlinapplication.entity.HttpResult
import com.wyman.kotlinapplication.service.ApiService
import com.wyman.libraryKotlin.base.BasePresenter
import com.wyman.libraryKotlin.base.RetrofitHelper
import com.wyman.libraryKotlin.net.ProgressObserver
import com.wyman.libraryKotlin.net.SubsriberOnNextListener

/**
 * @author wyman
 * @date  2018/9/3
 * description :
 */
class MainPresenter(private val context: Context) : BasePresenter<MainView>(context) {
    fun fetch(){


        invoke(RetrofitHelper.create(ApiService::class.java).getBanners(),ProgressObserver(
                context,
                object :SubsriberOnNextListener<HttpResult<List<Banner>>>{
                    override fun onNext(t: HttpResult<List<Banner>>) {
                        getView()?.showData(t.data)
                    }

                    override fun onError(e: Throwable) {

                    }

                }
        ))
    }
}