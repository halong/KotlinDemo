package com.example.kotlindemo.network.retrofit.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {  //此处object相当于声明一个静态内部类  这里可以理解为饿汉式单例
    //Retrofit使用三步走：获取Retrofit对象->创建ApiService服务接口->create
    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mGithubApiService: GithubApiService= mRetrofit.create(GithubApiService::class.java)
    //这样写避免调用方法时重复创建对象
    //在Kotlin中，默认访问权限为public，与Java不同
}