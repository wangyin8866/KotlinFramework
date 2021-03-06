package com.wyman.kotlinapplication.ui

import android.view.View
import com.wyman.kotlinapplication.R
import com.wyman.libraryKotlin.base.BaseFragment
import com.wyman.libraryKotlin.base.BasePresenter
import com.wyman.libraryKotlin.base.BaseView

/**
 * @author wyman
 * @date  2018/9/4
 * description :
 */
class InvestFragment : BaseFragment<BasePresenter<BaseView>, BaseView>() {
    companion object {
        fun getInstance(): InvestFragment = InvestFragment()
    }
    override fun initView(rootView: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_invest_main
    }

    override fun createPresenter(): BasePresenter<BaseView> {
       return BasePresenter(mContext)
    }

}