package com.zhangws.elderhelper.database

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class AppInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var icon: Int,
    var name: String,
    var system: Int = 0,
    var package_name: String,
    var appVersion:String,
    var add: Int = 0,
    var bar: Int = 0,
    var sort:Int=0
){


    @Ignore var drawable: Drawable?=null
}