package com.example.arouterdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.example.arouterdemo.R
import com.example.arouterdemo.bean.UserBean
import com.example.base.ARouterConstants

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/16
 */
@Route(path = ARouterConstants.ARouterTestFragment)
class ARouterTestFragment : Fragment() {
    @Autowired(name = "user2")
    @JvmField
    var user2: UserBean? = null//如果要传递Object实体类不能继承Serializable,Parcelable

    @Autowired
    @JvmField
    var list_user2: MutableList<UserBean>? = null

    @Autowired
    @JvmField
    var map_user2: MutableMap<String, UserBean>? = null

    lateinit var tvArouterDemo: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        var view: View = inflater.inflate(R.layout.fragment_arouter_test, container, false)
        tvArouterDemo = view.findViewById(R.id.tv_arouter_demo)
        ARouter.getInstance().inject(this)
        initView()
        return view
    }

    fun initView() {
        if (activity?.intent != null && (activity?.intent!!.getStringExtra("name") != null)) {
            var name = activity?.intent!!.getStringExtra("name")
            if (name == null) {
                name = ""
            }
            var age = activity?.intent!!.getIntExtra("age", 0)
            var sex = activity?.intent!!.getBooleanExtra("sex", false)
            var user = (activity?.intent!!.getBundleExtra("Bean"))?.getParcelable<UserBean>("UserBean")
            var str_user1 = " 用户1\n 姓名：${name}\n 年龄：${age}\n 性别：${if (sex) '男' else '女'}"
            if (user != null) {
                var str_user2 = " 用户2\n 姓名：${user.name}\n 年龄：${user.age}\n 性别：${if (user.sex) '男' else '女'}"
                str_user1 = str_user1 + "\n\n" + str_user2
            }
            tvArouterDemo.text = str_user1
            var s_user = activity?.intent!!.getSerializableExtra("s_bean") as UserBean
            var p_user = activity?.intent!!.getParcelableExtra<UserBean>("p_bean")
            LogUtils.d("ARouter", str_user1)
        }
    }
}