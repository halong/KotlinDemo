package com.example.kotlindemo.network.okhttp.ui.okhttp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.kotlindemo.network.okhttp.data.okhttp.OkhttpUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import android.os.AsyncTask.execute


class OkhttpViewModel : ViewModel() {
    private val data: MutableLiveData<String> = MutableLiveData()
    private val request = Request.Builder()
        .url("https://www.uc123.com/")
        .build()

    fun getData(): LiveData<String> = data

    fun getHTMLString() {
        //参数为接口的写法：object:Callback{} -> 右击generate/override methods
        OkhttpUtil.mOkHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                data.postValue(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                data.postValue(response.body()!!.string())
            }
        })
    }
}
