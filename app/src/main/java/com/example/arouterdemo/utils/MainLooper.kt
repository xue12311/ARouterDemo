package com.example.arouterdemo.utils

import android.os.Handler
import android.os.Looper

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/16
 */


class MainLooper protected constructor(looper: Looper) : Handler(looper) {
    companion object {
        val instance = MainLooper(Looper.getMainLooper())
        fun runOnUiThread(runnable: Runnable) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                runnable.run()
            } else {
                instance.post(runnable)
            }

        }
    }
}
