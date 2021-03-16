package com.zhangws.elderhelper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhangws.elderhelper.BaseApplication

@Database(entities = [AppInfo::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {

        private var instance: DataBase? = null

        fun getInstance(): DataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    BaseApplication.getContext(),
                    DataBase::class.java,
                    "local.db"
                ).allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}