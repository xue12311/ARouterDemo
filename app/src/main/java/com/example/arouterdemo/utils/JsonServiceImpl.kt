package com.example.arouterdemo.utils

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/13
 */
@Route(path = "/app_service/json")
class JsonServiceImpl : SerializationService {
    lateinit var gson: Gson
    override fun <T : Any?> json2Object(input: String?, clazz: Class<T>?): T {
        //自行选择反序列化方式，比如Gson、fastjson
        return gson.fromJson<T>(input, clazz)
//        return JSON.parseObject(input, clazz)
    }

    override fun init(context: Context?) {
        //可选，用于对序列化框架初始化
        gson = Gson()
    }

    override fun object2Json(instance: Any?): String {
        //自行选择序列化方式，比如Gson、fastjson
        return gson.toJson(instance)
//        return JSON.toJSONString(instance)
    }

    override fun <T : Any?> parseObject(input: String?, clazz: Type?): T {
        //自行选择反序列化方式，比如Gson、fastjson
        return gson.fromJson(input, clazz)
//        return JSON.parseObject(input, clazz)
    }
}