package com.example.kotlindemo.network.okhttp.data.okhttp

import okhttp3.Interceptor
import okhttp3.Response

/**
 *Created by halong on 2019/5/3
 *@description:偷梁换柱式自定义Interceptor
 *
 *     Interceptor分为Application Interceptor和Network Interceptor
 *     Application Interceptor--从缓存到本地
 *     NetworkInterception--从网络到缓存    可以在这里监听
 *
 *      response=mClient.newCall(request)
 *
 *     上传进度监听---在Request中替换RequestBody就可以了
 *     下载进度监听---需要在NetworkInterception中变换Response->ResponseBody,
 *     通过addNetworkInterception()修改Response->ResponseBody，从而实现下载进度监听
 *
 */
class ProgressInterceptor() : Interceptor {
    //Chain--链条  proceed--继续   链条连接request到response
    //偷梁换柱  换掉ResponseBody
    var progressListener: (Int) -> Unit = {}

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse=chain.proceed(chain.request())

        val myResponseBody=ProgressResponseBody(originalResponse.body()!!){
            progressListener(it)
        }

        val myResponse = originalResponse.newBuilder()
            .body(myResponseBody) //在这里偷梁换柱
            .build()

        return myResponse
    }
}