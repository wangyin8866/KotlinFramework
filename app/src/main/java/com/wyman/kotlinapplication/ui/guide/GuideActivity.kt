package com.wyman.kotlinapplication.ui.guide

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wyman.kotlinapplication.MainActivity
import com.wyman.kotlinapplication.R
import kotlinx.android.synthetic.main.activity_welcom_guide.*
import kotlinx.android.synthetic.main.guide4.view.*

/**
 * @author wyman
 * @date  2018/9/4
 * description :
 */
class GuideActivity : AppCompatActivity() {
    private var views = mutableListOf<View>()
    private val pageAdapter: GuideAdapter by lazy {
        GuideAdapter(views)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom_guide)
        init()
    }

    private fun init() {
        val  view1:View=layoutInflater.inflate(R.layout.guide1, null)
        val  view2:View=layoutInflater.inflate(R.layout.guide2, null)
        val  view3:View=layoutInflater.inflate(R.layout.guide3, null)
        val  view4:View=layoutInflater.inflate(R.layout.guide4, null)
        views.run {
            add(view1)
            add(view2)
            add(view3)
            add(view4)
        }
        welcome_pager.adapter = pageAdapter
        view4.btn_travel_join.setOnClickListener {
            startActivity(Intent(SplashActivity@ this, MainActivity::class.java))
        }
    }
}