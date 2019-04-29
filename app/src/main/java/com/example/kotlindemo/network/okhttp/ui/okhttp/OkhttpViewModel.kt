package com.example.kotlindemo.network.okhttp.ui.okhttp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.kotlindemo.network.okhttp.data.okhttp.OkhttpUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import android.os.Environment
import java.io.*


class OkhttpViewModel : ViewModel() {
    private val data: MutableLiveData<String> = MutableLiveData()

    fun getData(): LiveData<String> = data

    fun getHTMLString() {
        val request = Request.Builder()
            .url("https://www.uc123.com/")
            .build()
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


    fun downloadFile(){
        val request = Request.Builder()
            .url("https://gtd.alicdn.com/tfscom/TB1OySRSHrpK1RjSZTEwu3WAVXa")
            .build()
        OkhttpUtil.mOkHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                data.postValue(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                data.postValue("文件下载成功")

                //将下载的文件保存到本地
                val mInputStream = response.body()!!.byteStream()
                val mFilePath = Environment.getExternalStorageDirectory().path + "/Pictures/mm"
                val mFile = File(mFilePath)
                val mOutputStream = FileOutputStream(mFile)
                val b = ByteArray(1024)
                var i=mInputStream.read(b)
                while (i!=-1){
                    mOutputStream.write(b,0,i)
                    i=mInputStream.read(b)
                }
                mOutputStream.close()
                mInputStream.close()

            }
        })
    }
}
