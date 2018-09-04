package com.wyman.libraryKotlin.base

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ToastUtils
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.wyman.libraryKotlin.R
import me.yokeyword.fragmentation.*
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * @author wyman
 * @date  2018/9/3
 * description :
 */
 abstract class BaseActivity<T : BasePresenter<V>, V : BaseView> : RxAppCompatActivity(), ISupportActivity, BaseView {



    protected lateinit var mPresenter: T
    protected lateinit var mContext: Context
    protected var mDelegate: SupportActivityDelegate? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDelegate= SupportActivityDelegate(this)

        mDelegate!!.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        val layoutId: Int = getLayoutId()
        setContentView(layoutId)
        mContext = this
        mPresenter = createPresenter()

        mPresenter.attachView(this as V)

        initView()
        if (!NetworkUtils.isConnected()) {
            showNotNet()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDelegate!!.onDestroy()
        mPresenter.detachView()
    }

    abstract fun initView()

    abstract fun createPresenter(): T

    abstract fun getLayoutId(): Int
    override fun showNotNet() {
        ToastUtils.showShort(getString(R.string.tip_not_net))
    }
    override fun <T> bindToLife(): LifecycleTransformer<T> {
        return  this.bindToLifecycle()
    }
    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator) {
        mDelegate!!.fragmentAnimator
    }
    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    override fun getFragmentAnimator(): FragmentAnimator {
        return mDelegate!!.fragmentAnimator
    }

    override fun onBackPressedSupport() {
        mDelegate!!.onBackPressedSupport()
    }
    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    override fun extraTransaction(): ExtraTransaction{
       return mDelegate!!.extraTransaction()
    }
    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mDelegate!!.onCreateFragmentAnimator()
    }

    override fun getSupportDelegate(): SupportActivityDelegate? {
            return mDelegate
    }

    override fun post(runnable: Runnable) {
        mDelegate!!.post(runnable)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showSuccess(message: Any) {
    }

    override fun showFailed(message: String) {
    }

    /**
     * 得到位于栈顶Fragment
     */
    fun getTopFragment(): ISupportFragment {
        return SupportHelper.getTopFragment(supportFragmentManager)
    }

    /**
     * 获取栈内的fragment对象
     */
    fun <T : ISupportFragment> findFragment(fragmentClass: Class<T>): T  ?{
        return SupportHelper.findFragment(supportFragmentManager, fragmentClass)
    }

    /**
     * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
     */
    fun loadMultipleRootFragment(containerId: Int, showPosition: Int, vararg toFragments: ISupportFragment ?) {
        mDelegate!!.loadMultipleRootFragment(containerId, showPosition, *toFragments)
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     *
     *
     * 建议使用更明确的[.showHideFragment]
     *
     * @param showFragment 需要show的Fragment
     */
    fun showHideFragment(showFragment: ISupportFragment) {
        mDelegate!!.showHideFragment(showFragment)
    }

    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    fun showHideFragment(showFragment: ISupportFragment ?, hideFragment: ISupportFragment ?) {
        mDelegate!!.showHideFragment(showFragment, hideFragment)
    }
}