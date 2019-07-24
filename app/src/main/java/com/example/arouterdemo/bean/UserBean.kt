package com.example.arouterdemo.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/12
 */
@Parcelize
data class UserBean(
    var id: String,
    var name: String,
    var age: Int,
    var sex: Boolean
): Parcelable,Serializable{
    constructor() : this("", "", 0, false)
    constructor(name: String, age: Int) : this("", name, age, false)

    override fun toString(): String {
        return "姓名：${name},年龄：${age},性别：${if (sex) '男' else '女'}"
    }
}