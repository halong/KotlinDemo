package com.example.kotlindemo.network.okhttp.data.okhttp

import okhttp3.*
import okio.Okio
import java.io.File
import java.io.IOException

/**
 *Created by halong on 2019/5/3
 *@description:  封装的OkHttp工具类
 */
object MyOkHttpUtils {
    private val interceptor = ProgressInterceptor()

    private val mClient: OkHttpClient by lazy {
        //OkHttpClient对象，体格比较大，最好只创建一次
        OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
    }

    /**
     * 下载
     */
    fun download(url: String, callback: Callback) {
        val request = Request.Builder()
            .url(url)
            .build()
        mClient.newCall(request).enqueue(callback)
    }

    /**
     * 上传
     */
    fun upload(url: String, body: RequestBody, callback: Callback) {
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        mClient.newCall(request).enqueue(callback)
    }


    //下载文件带有进度监听   --addNetworkInterceptor(interceptor) --
    fun downloadFileWithProgress(url: String, saveFolder: File, listener: ProgressListener) {
        val fileName = getNameFromUrl(url)

        interceptor.progressListener = {
            listener.onProgress(it)
        }//NOTE：运行时调用 运行时访问
        // --解释：虽然OkHttpClient已经创建好了，但是在下载文件时调用拦截器，仍是实时调用其方法

        val request = Request.Builder()
            .url(url)
            .build()

        mClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val file = File(saveFolder, fileName)
                val bufferSink = Okio.buffer(Okio.sink(file))
                bufferSink.writeAll(response.body()!!.source())
                bufferSink.flush()
                listener.onSuccess(response)
            }

            override fun onFailure(call: Call, e: IOException) {
                listener.onFailure(e)
            }
        })
    }

    private fun getNameFromUrl(url: String): String {
        var name = url.trim(' ', '/')
        name = name.substring(name.lastIndexOf('/'))
        return name
    }

}


