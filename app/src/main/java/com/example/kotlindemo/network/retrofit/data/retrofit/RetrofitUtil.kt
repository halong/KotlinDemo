package com.example.kotlindemo.network.retrofit.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil{
        var mRetrofit:Retrofit=Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getGithubApiService()= mRetrofit.create(GithubApiService::class.java)
}