package com.example.arouterdemo.activity

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.StringUtils
import com.example.arouterdemo.R
import com.example.arouterdemo.R.layout.activity_webview
import com.example.base.ARouterConstants

/**
 *
 * @author: xue
 * @description please add a description here
 * @date: 2019/7/16
 */
@Route(path = ARouterConstants.WebviewActivity)
class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_webview)
        val webview = findViewById<WebView>(R.id.webview)
        var url: String = intent.getStringExtra("url")
        if (!StringUtils.isEmpty(url)) {
            webview.loadUrl(url)
        }
    }

}