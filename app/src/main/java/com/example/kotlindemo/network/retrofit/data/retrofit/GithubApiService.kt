package com.example.kotlindemo.network.retrofit.data.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Created by halong on 2019/4/20
 *@description:
 */
interface GithubApiService {
    @GET("users/{user}/repos")
    fun getResponseBody(@Path("user") user:String):Call<ResponseBody>
}