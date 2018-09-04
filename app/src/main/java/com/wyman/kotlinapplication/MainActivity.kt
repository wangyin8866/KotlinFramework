package com.wyman.kotlinapplication

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.wyman.kotlinapplication.ui.HomeFragment
import com.wyman.kotlinapplication.ui.InvestFragment
import com.wyman.kotlinapplication.ui.MyFragment
import com.wyman.libraryKotlin.base.BaseActivity
import com.wyman.libraryKotlin.base.BasePresenter
import com.wyman.libraryKotlin.base.BaseView
import kotlinx.android.synthetic.main.public_bottom_main.*
import me.yokeyword.fragmentation.ISupportFragment

/**
 * @author wyman
 * @date  2018/9/4
 * description :
 */
class MainActivity : BaseActivity<BasePresenter<BaseView>, BaseView>() {
    private val BOTTOM_INDEX: String = "bottom_index"
    private val mFragments = arrayOfNulls<ISupportFragment>(4)

    private var currentPage: Int = 0

    override fun initView() {
        val homeFragment: HomeFragment? = findFragment(HomeFragment::class.java)
        val investFragment: InvestFragment? = findFragment(InvestFragment::class.java)
        val myFragment: MyFragment? = findFragment(MyFragment::class.java)
        if (homeFragment == null || investFragment == null || myFragment == null) {
            mFragments[0] = HomeFragment.getInstance()
            mFragments[1] = InvestFragment.getInstance()
            mFragments[2] = MyFragment.getInstance()
            mFragments[3] = HomeFragment.getInstance()
            loadMultipleRootFragment(R.id.layout_fragment, currentPage, mFragments[0], mFragments[1], mFragments[2], mFragments[3])
        } else {
            mFragments[0] = homeFragment
            mFragments[1] = investFragment
            mFragments[2] = myFragment
            mFragments[3] = homeFragment

        }
        setTabSelection(currentPage)


        id_tab_ll_01.run { setOnClickListener(onClickListener) }
        id_tab_ll_02.run { setOnClickListener(onClickListener) }
        id_tab_ll_03.run { setOnClickListener(onClickListener) }
        id_tab_ll_04.run { setOnClickListener(onClickListener) }
    }


    override fun createPresenter(): BasePresenter<BaseView> {
        return BasePresenter(mContext)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(BOTTOM_INDEX, currentPage)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt(BOTTOM_INDEX)
        }
    }

    private fun setTabSelection(currentPage: Int) {
        //选中前清除状态
        restView()

        when (currentPage) {
            0 -> {
                id_tab_iv_01.setImageResource(R.drawable.ic_home_checked)
                id_tab_tv_01.setTextColor(resources.getColor(R.color.tv_navigate_checked))
            }
            1 -> {
                id_tab_iv_02.setImageResource(R.drawable.ic_product_checked)
                id_tab_tv_02.setTextColor(resources.getColor(R.color.tv_navigate_checked))
            }
            2 -> {
                id_tab_iv_03.setImageResource(R.drawable.ic_baina_checked)
                id_tab_tv_03.setTextColor(resources.getColor(R.color.tv_navigate_checked))
            }
            3 -> {
                id_tab_iv_04.setImageResource(R.drawable.ic_my_checked)
                id_tab_tv_04.setTextColor(resources.getColor(R.color.tv_navigate_checked))
            }
        }
    }

    private fun restView() {
        id_tab_iv_01.setImageResource(R.drawable.ic_home)
        id_tab_iv_02.setImageResource(R.drawable.ic_product)
        id_tab_iv_03.setImageResource(R.drawable.ic_baina)
        id_tab_iv_04.setImageResource(R.drawable.ic_my)
        id_tab_tv_01.setTextColor(resources.getColor(R.color.tv_navigate))
        id_tab_tv_02.setTextColor(resources.getColor(R.color.tv_navigate))
        id_tab_tv_03.setTextColor(resources.getColor(R.color.tv_navigate))
        id_tab_tv_04.setTextColor(resources.getColor(R.color.tv_navigate))
    }
    private val onClickListener:View.OnClickListener= View.OnClickListener {
        return@OnClickListener when(it.id){
            R.id.id_tab_ll_01->{
                showHideFragment(mFragments[0], mFragments[currentPage])
                currentPage = 0
                setTabSelection(currentPage)
            }
            R.id.id_tab_ll_02->{
                showHideFragment(mFragments[1], mFragments[currentPage])
                currentPage = 1
                setTabSelection(currentPage)
            }
            R.id.id_tab_ll_03->{
                showHideFragment(mFragments[2], mFragments[currentPage])
                currentPage = 2
                setTabSelection(currentPage)
            }
            R.id.id_tab_ll_04->{
                showHideFragment(mFragments[3], mFragments[currentPage])
                currentPage = 3
                setTabSelection(currentPage)
            }
            else -> {
                setTabSelection(currentPage)
            }

        }
    }
    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()

                ToastUtils.showShort(getString(R.string.exit_tip))
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}