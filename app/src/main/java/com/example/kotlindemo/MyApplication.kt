package com.example.kotlindemo

import android.app.Application
import android.content.Context
import com.example.kotlindemo.network.rxhttp.RxHttpRequestSetting
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.zhy.http.okhttp.OkHttpUtils
import per.goweii.rxhttp.core.RxHttp

/**
 *Created by halong on 2019/4/26
 *@description:
 */
class MyApplication :Application() {
    companion object{
        private lateinit var mApplication:Context

        fun getContext() = mApplication
    }
    override fun onCreate() {
        super.onCreate()
        mApplication=this.applicationContext

        //初始化Logger
        Logger.addLogAdapter(AndroidLogAdapter())

        //初始化RxHttp
        RxHttp.init(this)
        RxHttp.initRequest(RxHttpRequestSetting())

    }
}