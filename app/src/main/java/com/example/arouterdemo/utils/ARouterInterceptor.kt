package com.example.arouterdemo.utils

import android.app.AlertDialog
import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.blankj.utilcode.util.LogUtils
import com.example.arouterdemo.activity.MainActivity
import com.example.base.ARouterConstants

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/16
 */
@Interceptor(priority = 7)
class ARouterInterceptor : IInterceptor {
    lateinit var mContext: Context
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        if (mContext != null) {
            if (postcard?.path.equals(ARouterConstants.ARouterInterceptorActivity)) {
                LogUtils.e("当前线程A：${Thread.currentThread().name}")
                MainLooper.runOnUiThread(object : Runnable {
                    override fun run() {
                        LogUtils.e("当前线程B：${Thread.currentThread().name}")
                        AlertDialog.Builder(MainActivity.getThis())
                            .setCancelable(false)
                            .setTitle("温馨提醒")
                            .setMessage("想要跳转到Test4Activity么？(触发了\"/inter/test1\"拦截器，拦截了本次跳转)")
                            .setNegativeButton(
                                "不拦截"
                            ) { dialog, which -> callback?.onContinue(postcard) }
                            .setNeutralButton(
                                "拦截"
                            ) { dialog, which -> callback?.onInterrupt(null) }
                            .setPositiveButton("添加拦截参数") { dialog, which ->
                                postcard?.withString("extra", "我是在拦截器中附加的参数")
                                callback?.onContinue(postcard)
                            }.create().show()
                    }
                })
                LogUtils.e("当前线程C：${Thread.currentThread().name}")
            } else {
                callback?.onContinue(postcard)
            }
        } else {
            callback?.onContinue(postcard)
        }
    }

    override fun init(context: Context?) {
        if (context != null) {
            mContext = context
        }
    }

}