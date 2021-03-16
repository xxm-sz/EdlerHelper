package com.zhangws.elderhelper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import com.zhangws.elderhelper.database.AppInfo
import com.zhangws.elderhelper.database.DataBase

/**
 * @param type 0 所有应用 1 第三方应用 2 系统应用 3 已添加应用
 */
fun getAppList(type: Int): MutableList<AppInfo>? {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)

    val list = BaseApplication.getContext().packageManager.getInstalledPackages(
        PackageManager.GET_ACTIVITIES
    )


    val iterator = list.iterator()

    val appList = mutableListOf<AppInfo>()

    val appDao = DataBase.getInstance().appDao()

    while (iterator.hasNext()) {
        val next = iterator.next()

        if (type == 1) {
            if ((next.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 1) {
                continue
            }
        } else if (type == 2) {
            if ((next.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 1 ||
                next.applicationInfo.icon == 0 || next.applicationInfo.icon == 2131623936 || next.applicationInfo.icon == 2131361792
            ) {
                continue
            }
        } else if (type == 3 && appDao.findApp(next.packageName) == null) {
            continue
        }

        var appInfo = appDao.findApp(next.packageName)

        if (appInfo==null){
            appInfo = AppInfo(
                icon = next.applicationInfo.icon,
                name = next.applicationInfo.loadLabel(BaseApplication.getContext().packageManager)
                    .toString() ?: "",
                package_name = next.packageName,
                appVersion = next.versionName
            )

            if ((next.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 1) {
                appInfo.system = 1
            }
        }

        appInfo.drawable =
            next.applicationInfo.loadIcon(BaseApplication.getContext().packageManager)

        appList.add(appInfo)
    }
    if (type == 3) {
        appList.sortBy { it.sort }
    } else {
        appList.sortByDescending { it.add }
    }

    return appList
}