package com.example.arouterdemo.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.arouterdemo.MyApplication
import com.example.arouterdemo.R
import com.example.arouterdemo.R.layout.activity_main
import com.example.arouterdemo.bean.UserBean
import com.example.arouterdemo.utils.HelloService
import com.example.arouterdemo.utils.SingleService
import com.example.base.ARouterConstants
import com.example.base.BaseApplication

@Route(path = ARouterConstants.MainActivity)
class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private lateinit var activity: Activity
        fun getThis(): Activity {
            return activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        setContentView(activity_main)
    }

    var navCallback = object : NavCallback() {
        override fun onArrival(postcard: Postcard?) {
            LogUtils.d("ARouter跳转了")
        }

        override fun onFound(postcard: Postcard?) {
            super.onFound(postcard)
            LogUtils.d("ARouter找到了")
        }

        override fun onLost(postcard: Postcard?) {
            super.onLost(postcard)
            LogUtils.d("ARouter找不到")
        }

        override fun onInterrupt(postcard: Postcard?) {
            super.onInterrupt(postcard)
            LogUtils.d("ARouter被拦截了")
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.but_init_arouter -> BaseApplication.instance().initARouter()//初始化ARouter
                R.id.but_destroy_arouter -> ARouter.getInstance().destroy()//关闭ARouter
                R.id.but_arouter_normal_navigation -> ARouter.getInstance()
                    .build(ARouterConstants.ARouterTestActivity).navigation()
                R.id.but_arouter_normal_navigation_with_params -> {
                    var user1: UserBean = UserBean("001", "李四", 12, false)
                    var user2: UserBean = UserBean("王五", 23)
                    var list_user2 = mutableListOf<UserBean>(user1, user2)
                    var map_user2 = mutableMapOf<String, UserBean>("user1" to user1)
                    map_user2.put("user2", user2)
                    var bundle: Bundle = Bundle()
                    bundle.putParcelable("UserBean", user1)
                    ARouter.getInstance().build(ARouterConstants.ARouterTestActivity)
                        .withString("name", "张三")
                        .withInt("age", 28)
                        .withBoolean("sex", true)
                        .withSerializable("s_bean", user1)
                        .withParcelable("p_bean", user1)
                        .withBundle("Bean", bundle)
                        .withObject("user2", user2)
                        .withObject("list_user2", list_user2)
                        .withObject("map_user2", map_user2)
                        .navigation()
                }
                R.id.but_arouter_normal_navigation_with_params_url -> {
                    ARouter.getInstance().build(ARouterConstants.WebviewActivity)
                        .withString("url", "file:///android_asset/schame-test.html").navigation()
//                    var uri = Uri.parse("arouter://m.aliyun.com${ARouterConstants.ARouterTestActivity}?name=老王&age=23&sex=true")
//                    ARouter.getInstance().build(uri).withString("url", "file:///android_asset/schame-test.html").navigation()
                }

                R.id.but_arouter_normal_navigation_with_params_fragment -> {
                    var fragment: Fragment =
                        ARouter.getInstance().build(ARouterConstants.ARouterTestFragment)
                            .navigation() as Fragment
                    ToastUtils.showShort("找到Fragment：${fragment.toString()}")
                }
                R.id.but_arouter_normal_navigation_oldVersionAnim -> {
                    ARouter.getInstance().build(ARouterConstants.ARouterTestActivity)
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation()
                }
                R.id.but_arouter_normal_navigation_newVersionAnim -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        var compat: ActivityOptionsCompat =
                            ActivityOptionsCompat.makeScaleUpAnimation(
                                v,
                                v.width / 2,
                                v.height / 2,
                                0,
                                0
                            )
                        ARouter.getInstance().build(ARouterConstants.ARouterTestActivity)
                            .withOptionsCompat(compat).navigation()
                    } else {
                        ToastUtils.showShort("API < 16,不支持新版本动画")
                        ARouter.getInstance().build(ARouterConstants.ARouterTestActivity)
                            .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                            .navigation()
                    }
                }
                R.id.but_arouter_normal_navigation_interceptor -> {
                    ARouter.getInstance().build(ARouterConstants.ARouterInterceptorActivity)
                        .navigation()
                }
                R.id.but_arouter_normal_navigation_autoInject -> {
                    ARouter.getInstance().build(ARouterConstants.ARouterTestActivity)
                        .navigation(this, 1291)
                }
                R.id.but_arouter_normal_navigation_by_name -> {
                    (ARouter.getInstance().build(ARouterConstants.ARouterTestHelloServiceImpl)
                        .navigation() as HelloService).sayHello(
                        "调用服务name"
                    )
                }
                R.id.but_arouter_normal_navigation_by_type -> {
                    ARouter.getInstance().navigation(HelloService::class.java).sayHello("调用服务type")
                }
                R.id.but_arouter_normal_navigation_call_single -> {
                    ARouter.getInstance().navigation(SingleService::class.java).sayHello("调用单类服务")
                }
                R.id.but_arouter_normal_navigation_fail -> {
                    ARouter.getInstance().build("/xxx/xxx").navigation(this, navCallback)
                }
                R.id.but_arouter_normal_navigation_java -> {
                    ARouter.getInstance().build(ARouterConstants.ARouterTestActivityJava)
                        .navigation(this, navCallback)
                }
                R.id.but_arouter_normal_navigation_with_params_java -> {
                    ARouter.getInstance().build(ARouterConstants.ARouterTestActivityJava)
                        .withString("name", "Java")
                        .withInt("age", 12)
                        .withBoolean("sex", true)
                        .navigation(this, navCallback)
                }
                R.id.but_arouter_normal_navigation_kotlin -> {
                    ARouter.getInstance().build(ARouterConstants.ARouterTestActivityKotlin)
                        .navigation()
                }
                R.id.but_arouter_normal_navigation_with_params_kotlin -> {
                    ARouter.getInstance().build(ARouterConstants.ARouterTestActivityKotlin)
                        .withString("name", "Kotlin")
                        .withInt("age", 16)
                        .withBoolean("sex", true)
                        .navigation()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1291 -> {
                ToastUtils.showShort("Activity_Result: ${requestCode}")
            }
        }
    }
}
