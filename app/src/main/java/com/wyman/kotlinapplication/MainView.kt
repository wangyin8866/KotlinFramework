package com.wyman.kotlinapplication

import com.wyman.kotlinapplication.entity.Banner
import com.wyman.libraryKotlin.base.BaseView

/**
 * @author wyman
 * @date  2018/9/4
 * description :
 */
interface MainView : BaseView {
    fun showData(list: List<Banner>)
}