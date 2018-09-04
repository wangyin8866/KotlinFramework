package com.wyman.libraryKotlin.net

/**
 * @author wyman
 * @date  2018/9/3
 * description :
 */
interface SubsriberOnNextListener<T> {
    fun onNext(t :T)
    fun onError(e:Throwable)
}