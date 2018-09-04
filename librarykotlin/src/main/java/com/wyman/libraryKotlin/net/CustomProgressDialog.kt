package com.wyman.libraryKotlin.net

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import com.wyman.libraryKotlin.R
import kotlinx.android.synthetic.main.layout_custom_loading.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
/**
 * @author wyman
 * @date  2018/9/3
 * description :
 */
class CustomProgressDialog(context: Context, int: Int) : Dialog(context, int) {
    private var mAnimationDrawable: AnimationDrawable? = null
    private var mContext: Context? = context


    init {
        setContentView(R.layout.layout_custom_loading)
        window.attributes.gravity = Gravity.CENTER
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var mCustomProgressDialog: CustomProgressDialog
        fun createDialog(context: Context): CustomProgressDialog {
            mCustomProgressDialog = CustomProgressDialog(context,R.style.CustomProgressDialog)
            mCustomProgressDialog.setCanceledOnTouchOutside(false)
            return mCustomProgressDialog
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        iv_progress.setImageDrawable(mContext?.resources?.getDrawable(R.drawable.anim_loading))
//        iv_progress.setImageResource(R.drawable.anim_loading)
//        iv_progress.setImageDrawable(mContext!!.resources.getDrawable(R.drawable.anim_loading,null))
        mAnimationDrawable = iv_progress.drawable as AnimationDrawable?
        mAnimationDrawable?.start()

    }

    override fun dismiss() {
        super.dismiss()
        mAnimationDrawable?.stop()
        mAnimationDrawable = null
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (mContext != null && mContext is Activity) {
            if (!(mContext as Activity).isFinishing) {
                dismiss()
            }
        }
    }
}