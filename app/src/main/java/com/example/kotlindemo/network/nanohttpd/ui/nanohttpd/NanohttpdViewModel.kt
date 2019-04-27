package com.example.kotlindemo.network.nanohttpd.ui.nanohttpd

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Environment
import android.util.Log
import com.example.kotlindemo.network.nanohttpd.data.nanohttpd.MyNanohttpd
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class NanohttpdViewModel : ViewModel() {
    private val client = OkHttpClient.Builder()
        .build()
    private val data = MutableLiveData<String>()

    fun getData(): LiveData<String> = data


    fun startServe() {
        MyNanohttpd(8079).start()
        data.postValue("服务已被开启！")
    }


    fun visitServeByGetMethod() {
        val request = Request.Builder()
            .url("http://localhost:8079?sdgah=中文&daskjd=fjpoooo")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                data.postValue(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                data.postValue(response.body()!!.string())
            }
        })
    }

    fun visitServeByPostMethod() {
        val mFilePath = Environment.getExternalStorageDirectory().path + "/Pictures/d.jpg"
        val mFile = File(mFilePath)
        val mBody = RequestBody.create(MediaType.parse("image/jpg"), mFile)
        val body = MultipartBody.Builder()    //OkHttp实现表单上传
            .setType(MultipartBody.FORM)      //setType(MultipartBody.FORM)上传含文件的表单一定要加上
            .addFormDataPart("name0", "test2.jpg", mBody)  //而且addFormDataPart()格式必须如此
            .addFormDataPart("name1", "test1.jpg", mBody)
            .build()

//        val myFilePath = Environment.getExternalStorageDirectory().path + "/Pictures/f.txt"
//        val myFile = File(myFilePath)
//        val myFileStream =FileOutputStream(myFile)
//        myFileStream.write("zhongwen&中文".toByteArray())
//        val body = RequestBody.create(MediaType.parse("text"),myFile)

//        val body=RequestBody.create(MediaType.parse("text/String"),"sadfh&中文")

        val request = Request.Builder()
            .url("http://localhost:8079")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                data.postValue(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                data.postValue(response.body()!!.string())
            }
        })

    }
}
