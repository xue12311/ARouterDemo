package com.example.module_java;

import com.blankj.utilcode.util.LogUtils;
import com.example.base.IBaseApplication;

public class ModuleJavaApplication implements IBaseApplication {
    @Override
    public void initModuleApplication() {
        LogUtils.e("初始化 模块 Java");
    }
}
