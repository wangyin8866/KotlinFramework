package com.wyman.kotlinapplication.ui.guide

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * @author wyman
 * @date  2018/9/4
 * description :
 */
class GuideAdapter(private val list: List<View>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(list[position])
        return list[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View?)
    }
}