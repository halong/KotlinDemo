package com.example.kotlindemo.network.okhttp.data.okhttp

import okhttp3.OkHttpClient

/**
 *Created by halong on 2019/4/21
 *@description:
 */
object OkhttpUtil {//相当于静态内部类 单例的两个条件：初次调用时加载，线程安全
    val mOkHttpClient:OkHttpClient = OkHttpClient()
}