package com.example.arouterdemo.utils

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider
import com.blankj.utilcode.util.ToastUtils
import com.example.base.ARouterConstants

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/16
 */
@Route(path = ARouterConstants.ARouterTestSingleService,name = "hello服务")
class SingleService : IProvider {
    var mContext: Context? = null
    fun sayHello(name: String) {
        ToastUtils.showShort("服务管理：${name}")
    }

    override fun init(context: Context?) {
        mContext = context
    }
}