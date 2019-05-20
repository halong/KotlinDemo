package com.example.kotlindemo.network.okhttp.data.okhttp

import okhttp3.Response
import java.io.IOException

/**
 *Created by halong on 2019/5/3
 *@description:文件下载进度监听回调接口
 */
interface ProgressListener {
    fun onSuccess(response: Response)
    fun onFailure(e: IOException)
    fun onProgress(percent: Int)
}