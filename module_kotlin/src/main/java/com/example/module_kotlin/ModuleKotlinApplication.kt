package com.example.module_kotlin

import com.blankj.utilcode.util.LogUtils
import com.example.base.IBaseApplication

class ModuleKotlinApplication : IBaseApplication {
    override fun initModuleApplication() {
        LogUtils.e("初始化 模块 Kotlin")
    }
}