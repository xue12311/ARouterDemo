package com.example.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

open class BaseApplication : Application(), IBaseApplication {
    companion object {
        //只能初始化一次
        private var instance: BaseApplication by DelegatesExt.notNullSingleValue()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initModuleApplication()
        initARouter()
    }

    /**
     * 初始化ARouter
     */
 fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()// 打印日志
            ARouter.openDebug()//开启调试模式
        }
        ARouter.init(instance())
    }

    /**
     * 初始化其他模块的Application
     */
    override fun initModuleApplication() {
        for (module in ModuleConfig.modules_application) {
            try {
                val clazz: Class<*> = Class.forName(module)
                val obj = clazz.newInstance()
                if (obj is IBaseApplication) {
                    //初始化各个模块的Application
                    obj.initModuleApplication()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}