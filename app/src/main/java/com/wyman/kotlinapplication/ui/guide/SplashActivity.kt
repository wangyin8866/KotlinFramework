package com.wyman.kotlinapplication.ui.guide

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wyman.kotlinapplication.MainActivity
import com.wyman.kotlinapplication.config.Constant
import com.wyman.libraryKotlin.utils.Preference

/**
 * @author wyman
 * @date  2018/9/4
 * description :
 */
class SplashActivity : AppCompatActivity() {

    private var isFirst: Boolean by Preference(Constant.KEY_WELCOME, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        if (isFirst) {
            startActivity(Intent(SplashActivity@ this, MainActivity::class.java))
        } else {
            isFirst = true
            startActivity(Intent(SplashActivity@ this, GuideActivity::class.java))

        }
    }
}