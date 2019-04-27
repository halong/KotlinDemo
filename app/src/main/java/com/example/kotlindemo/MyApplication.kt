package com.example.kotlindemo

import android.app.Application
import android.content.Context

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
    }
}