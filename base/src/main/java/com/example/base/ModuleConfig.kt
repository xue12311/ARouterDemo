package com.example.base

object ModuleConfig {
    private val ModuleJavaPath: String = "com.example.module_java.ModuleJavaApplication"
    private val ModuleKotlinPath: String = "com.example.module_kotlin.ModuleKotlinApplication"
    private val PAY: String = "com.linda.pay.PayApplication"

    val modules_application = arrayOf(ModuleJavaPath, ModuleKotlinPath, PAY)
}