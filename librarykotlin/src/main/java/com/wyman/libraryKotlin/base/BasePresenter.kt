package com.wyman.libraryKotlin.base

import android.content.Context
import com.wyman.libraryKotlin.net.RetryWithDelay
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 * @author wyman
 * @date  2018/9/3
 * description :
 */
open class BasePresenter<T : BaseView>(context: Context) {
    private var mVReference: WeakReference<T>? = null
    protected fun <V> invoke(observable: Observable<V>, observer: Observer<V>) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getView()?.bindToLife())
                .retryWhen(RetryWithDelay())
                .subscribe(observer)
    }

    /**
     * 关联View
     */
    fun attachView(view: T) {
        mVReference = WeakReference(view)


    }

    /**
     * 解除关联
     */
    fun detachView() {

        mVReference?.clear()
        mVReference = null
    }

    fun getView(): T? {
        return mVReference?.get()

    }
}