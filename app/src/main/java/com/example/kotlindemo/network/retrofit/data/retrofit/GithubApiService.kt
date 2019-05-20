package com.example.kotlindemo.network.retrofit.data.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Created by halong on 2019/4/20
 *@description:
 */

//eg:https://api.github.com/users/halong/repos
interface GithubApiService {
    @GET("users/{user}/repos")   //Call<T>   T 要么为ResponseBody，要么为实体类及其集合
    fun getResponseBody(@Path("user") user:String):Call<ResponseBody>

    @GET("users/{user}/repos")
    fun getRepos(@Path("user")user: String):Call<List<Repo>>
}