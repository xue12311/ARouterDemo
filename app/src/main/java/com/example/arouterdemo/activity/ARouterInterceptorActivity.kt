package com.example.arouterdemo.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.StringUtils
import com.example.arouterdemo.R
import com.example.base.ARouterConstants

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/16
 */
@Route(path = ARouterConstants.ARouterInterceptorActivity)
class ARouterInterceptorActivity : AppCompatActivity() {
    @Autowired(name = "extra")
    @JvmField
    var extra: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity_arouter_test)
        val tv_arouter_demo = findViewById<TextView>(R.id.tv_arouter_demo)
        tv_arouter_demo.text = "拦截器拦截界面"
        ARouter.getInstance().inject(this)
        if (!StringUtils.isEmpty(extra)) {
            tv_arouter_demo.text = "拦截参数：${extra}"
        }
    }
}