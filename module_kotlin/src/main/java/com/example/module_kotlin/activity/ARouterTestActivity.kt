package com.example.module_kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.StringUtils
import com.example.base.ARouterConstants
import com.example.module_kotlin.R.layout.activity_arouter_test
import kotlinx.android.synthetic.main.activity_arouter_test.*

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/16
 */
@Route(path = ARouterConstants.ARouterTestActivityKotlin)
class ARouterTestActivity : AppCompatActivity() {
    @Autowired(name = "name")
    @JvmField
    var name: String = ""

    @Autowired(name = "age")
    @JvmField
    var age: Int? = 0

    @Autowired(name = "sex")
    @JvmField
    var sex: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_arouter_test)
        ARouter.getInstance().inject(this)
        if(!StringUtils.isEmpty(name)){
        var str_user = " 用户1\n 姓名：${name}\n 年龄：${age}\n 性别：${if (sex) '男' else '女'}"
        tv_arouter_demo.text = str_user
    }}
}