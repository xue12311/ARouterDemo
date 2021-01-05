package com.example.arouterdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.arouterdemo.R.layout.kotlin_activity_arouter_test
import com.example.arouterdemo.bean.UserBean
import com.example.base.ARouterConstants
import kotlinx.android.synthetic.main.activity_arouter_test.*

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/12
 */
@Route(path = ARouterConstants.ARouterTestActivity)
class ARouterTestActivity : AppCompatActivity() {
    @Autowired(name = "user2")
    @JvmField
    var user2: UserBean? = null//如果要传递Object实体类不能继承Serializable,Parcelable

    @Autowired
    @JvmField
    var list_user2: MutableList<UserBean>? = null

    @Autowired
    @JvmField
    var map_user2: MutableMap<String, UserBean>? = null

    @Autowired(name = "name")
    lateinit var name1: String

    @Autowired
    @JvmField
    var age: Int? = 0

    @Autowired(name = "age")
    @JvmField
    var age1: Int? = 0

    @Autowired
    @JvmField
    var sex: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(kotlin_activity_arouter_test)
        ARouter.getInstance().inject(this)
        if (intent != null && (intent.getStringExtra("name") != null)) {
            var name = intent.getStringExtra("name")
            if (name == null) {
                name = ""
            }
            var age = intent.getIntExtra("age", 0)
            var sex = intent.getBooleanExtra("sex", false)
            var user = (intent.getBundleExtra("Bean"))?.getParcelable<UserBean>("UserBean")
            var str_user1 = " 用户1\n 姓名：${name}\n 年龄：${age}\n 性别：${if (sex) '男' else '女'}"
            if (user != null) {
                var str_user2 = " 用户2\n 姓名：${user.name}\n 年龄：${user.age}\n 性别：${if (user.sex) '男' else '女'}"
                str_user1 = str_user1 + "\n\n" + str_user2
            }
            tv_arouter_demo.text = str_user1
//            var s_user = intent.getSerializableExtra("s_bean") as UserBean
//            var p_user = intent.getParcelableExtra<UserBean>("p_bean")
//            LogUtils.d("ARouter", str_user1)
        }
    }
}