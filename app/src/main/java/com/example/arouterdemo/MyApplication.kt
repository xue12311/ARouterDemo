package com.example.arouterdemo

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 *
 * @author: xue
 * @description 自定义Application
 * @date: 2019/7/12
 */

class MyApplication : Application() {
    companion object {
        var isDebug: Boolean = true
        private var instance: MyApplication? = null

        fun getInstance(): MyApplication {
            if (instance == null) {
                synchronized(MyApplication::class.java) {
                    if (instance == null) {
                        instance = MyApplication()
                    }
                }
            }
            return instance!!
        }

        fun initARouter() {
            if (isDebug) {
                ARouter.openLog()// 打印日志
                ARouter.openDebug()//开启调试模式
            }
            ARouter.init(getInstance())

        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this;
        initARouter()
    }
}