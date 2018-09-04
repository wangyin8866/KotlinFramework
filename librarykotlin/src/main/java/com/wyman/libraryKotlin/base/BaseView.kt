package com.wyman.libraryKotlin.base

import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * @author wyman
 * @date  2018/9/3
 * description :
 */
interface BaseView {

    //显示进度条
    fun showLoading()

    //隐藏进度条
    fun hideLoading()

    //请求成功
    fun showSuccess(message: Any)

    //请求失败
    fun showFailed(message: String)

    fun showNotNet()

    //绑定生命周期
    fun <T> bindToLife() :LifecycleTransformer<T>


}