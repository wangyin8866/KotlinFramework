package com.wyman.libraryKotlin.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ToastUtils
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.components.support.RxFragment
import com.wyman.libraryKotlin.R
import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragmentDelegate
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * @author wyman
 * @date  2018/9/3
 * description :
 */
abstract class BaseFragment<T : BasePresenter<V>, V : BaseView> : RxFragment(), ISupportFragment, BaseView {


    protected lateinit var mPresenter: T
    protected lateinit var mContext: Context
    protected val mDelegate: SupportFragmentDelegate = SupportFragmentDelegate(this)
    private var rootView: View? = null

    companion object {
        private const val STATE_SAVE_IS_HIDDEN: String = "STATE_SAVE_IS_HIDDEN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mDelegate.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        mContext = mDelegate.activity
        mPresenter = createPresenter()
        mPresenter.attachView(this as V)
        if (!NetworkUtils.isConnected()) {
            showNotNet()
        }
        if (savedInstanceState != null) {
            val isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN)
            val ft = fragmentManager!!.beginTransaction()
            if (isSupportHidden) {
                ft.hide(this)
            } else {
                ft.show(this)
            }
            ft.commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mDelegate.onSaveInstanceState(outState)
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflaterView(inflater, container)
        initView(rootView)
        return rootView
    }

    abstract fun initView(rootView: View?)

    private fun inflaterView(inflater: LayoutInflater, container: ViewGroup?) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDelegate.onDestroy()
        mPresenter.detachView()
    }

    abstract fun getLayoutId(): Int

    abstract fun createPresenter(): T

    override fun setFragmentResult(resultCode: Int, bundle: Bundle?) {
        mDelegate.setFragmentResult(resultCode, bundle)
    }

    override fun onSupportInvisible() {
        mDelegate.onSupportInvisible()
    }

    override fun onNewBundle(args: Bundle?) {
        mDelegate.onNewBundle(args)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mDelegate.onAttach(activity)
    }

    override fun extraTransaction(): ExtraTransaction {
        return mDelegate.extraTransaction()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mDelegate.onCreateFragmentAnimator()
    }


    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        mDelegate.onFragmentResult(requestCode, resultCode, data)
    }

    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator?) {
        mDelegate.fragmentAnimator
    }

    /**
     * 同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
     */
    override fun onLazyInitView(savedInstanceState: Bundle?) {
        mDelegate.onLazyInitView(savedInstanceState)

    }

    override fun getFragmentAnimator(): FragmentAnimator {
        return mDelegate.fragmentAnimator
    }

    override fun isSupportVisible(): Boolean {
        return mDelegate.isSupportVisible
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        mDelegate.onEnterAnimationEnd(savedInstanceState)
    }

    override fun onSupportVisible() {
        mDelegate.onSupportVisible()
    }

    override fun onBackPressedSupport(): Boolean {
        return mDelegate.onBackPressedSupport()
    }

    override fun getSupportDelegate(): SupportFragmentDelegate {
        return mDelegate
    }

    /**
     * 添加NewBundle,用于启动模式为SingleTask/SingleTop时
     */
    override fun putNewBundle(newBundle: Bundle?) {
        mDelegate.putNewBundle(newBundle)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mDelegate.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mDelegate.onResume()
    }

    override fun onPause() {
        super.onPause()
        mDelegate.onPause()
    }

    override fun onDestroyView() {
        mDelegate.onDestroyView()
        super.onDestroyView()
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        mDelegate.onHiddenChanged(hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mDelegate.setUserVisibleHint(isVisibleToUser)
    }

    override fun post(runnable: Runnable?) {
        mDelegate.post(runnable)
    }

    override fun enqueueAction(runnable: Runnable?) {
        mDelegate.enqueueAction(runnable)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showSuccess(message: Any) {
    }

    override fun showFailed(message: String) {
    }

    override fun showNotNet() {
        ToastUtils.showShort(getString(R.string.tip_not_net))
    }

    override fun <T> bindToLife(): LifecycleTransformer<T> {
        return this.bindToLifecycle()
    }
}