package com.example.arouterdemo.utils

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider
import com.blankj.utilcode.util.ToastUtils
import com.example.base.ARouterConstants

@Route(path = ARouterConstants.ARouterTestHelloServiceImpl,name = "hello服务")
class HelloServiceImpl : HelloService {
    var mContext: Context? = null
    override fun sayHello(name: String) {
        ToastUtils.showShort("服务管理：${name}")
    }

    override fun init(context: Context?) {
        mContext = context
    }
}

interface HelloService: IProvider {
    fun sayHello(name:String)
}