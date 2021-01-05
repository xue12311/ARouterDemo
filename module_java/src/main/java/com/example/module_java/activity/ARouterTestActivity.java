package com.example.module_java.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.example.base.ARouterConstants;
import com.example.module_java.R;

@Route(path = ARouterConstants.ARouterTestActivityJava)
public class ARouterTestActivity extends AppCompatActivity {
    @Autowired(name = "name")
    String name = "";
    @Autowired(name = "age")
    int age = 0;
    @Autowired(name = "sex")
    boolean sex = false;

    TextView tvARouterDemo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.java_activity_arouter_test);
        ARouter.getInstance().inject(this);
        tvARouterDemo = findViewById(R.id.tv_arouter_demo);
        if (!StringUtils.isEmpty(name)) {
            String str_sex = "男";
            if (sex) {
                str_sex = "男";
            } else {
                str_sex = "女";
            }
            String str_demo = " 用户1\n 姓名：" + name + "\n 年龄：" + age + "\n 性别：" + str_sex;
            tvARouterDemo.setText(str_demo);
        }
    }
}
