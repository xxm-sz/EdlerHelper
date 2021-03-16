package com.zhangws.elderhelper.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhangws.elderhelper.fragment.AddedFragment
import com.zhangws.elderhelper.fragment.NormalAppFragment
import com.zhangws.elderhelper.fragment.SystemAppFragment

class AppListAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AddedFragment()
            }
            1 -> {
                NormalAppFragment()
            }
            else -> {
                SystemAppFragment()
            }
        }
    }
}