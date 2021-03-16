package com.zhangws.elderhelper

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zhangws.elderhelper.adapter.AppListAdapter
import com.zhangws.elderhelper.database.AppDao


class AppListActivity : AppCompatActivity() {


    lateinit var tabLayout: TabLayout

    lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewpager)
        viewPager.adapter=AppListAdapter(this)
        viewPager.offscreenPageLimit=3
        viewPager.currentItem=1


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            var text="全部"
            when(position){
                0->text="已添加到桌面"
                1->text="第三方应用"
                2->text="系统应用"
            }
            tab.text=text
        }.attach()

        initView()

        initListener()
    }

    private fun initView() {
        findViewById<TextView>(R.id.tvTitle).text="桌面管理"
    }

    private fun initListener() {
        findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            finish()
        }
    }
}