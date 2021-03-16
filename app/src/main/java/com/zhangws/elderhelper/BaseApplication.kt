package com.zhangws.elderhelper

import android.app.Application
import android.content.Context

class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        private var context: Context? = null

        fun getContext(): Context {
            return context!!
        }
    }

}

