package com.example.kotlindemo.handler

import android.os.Handler
import android.os.Looper

/**
 *Created by halong on 2019/5/15
 *@description:
 */
class MyTest(private val callback: (Int) -> Unit) {
    init {
        //指定句柄在主线程
        val handler = Handler(Looper.getMainLooper()) {
            callback(it.what)
            true
        }

        //开个子线程
        Thread {
            var i = 0
            while (i < 9) {
                i++
                handler.sendEmptyMessage(i)
                Thread.sleep(1000)
            }
        }.start()
    }

}