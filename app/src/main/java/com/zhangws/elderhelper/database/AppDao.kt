package com.zhangws.elderhelper.database

import androidx.room.*

@Dao
interface AppDao {


    @Query("select * from AppInfo where package_name=:packageName")
    fun  findApp(packageName:String):AppInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveApp(appInfo: AppInfo)

    @Query("select * from AppInfo")
    fun getApp():List<AppInfo>

    @Delete
    fun removeApp(appInfo: AppInfo)

    @Update
    fun update( appInfo: List<AppInfo>)
}