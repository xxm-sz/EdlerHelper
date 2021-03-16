package com.zhangws.elderhelper.fragment

import androidx.lifecycle.ViewModel
import com.zhangws.elderhelper.database.AppInfo
import com.zhangws.elderhelper.getAppList

class AppModel : ViewModel() {

    fun getList(type: Int, callback: (MutableList<AppInfo>) -> Unit) {

        val list = getAppList(type) ?: mutableListOf()

        callback(list)
    }
}