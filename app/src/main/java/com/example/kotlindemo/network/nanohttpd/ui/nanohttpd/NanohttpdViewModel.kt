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
import java.net.URLEncoder

class NanohttpdViewModel : ViewModel() {
    private val client = OkHttpClient.Builder()
        .build()

    private val data = MutableLiveData<String>()

    private val imagePath = MutableLiveData<String>()

    private val mNanoHTTPD = MyNanohttpd(8079)

    fun getData(): LiveData<String> = data

    fun getImagePath():LiveData<String> =imagePath

    fun startServe() {
        mNanoHTTPD.start()
        data.postValue("服务已被开启！")
    }

    fun stopServe(){
        mNanoHTTPD.stop()
        data.postValue("服务已被关闭！")
    }

    fun downloadStringByGET() {
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


    /**
     * GET上传参数下载文件
     */
    fun downloadFileByGET() {
        val request = Request.Builder()
            .url("http://localhost:8079?name=下载文件")//这里会自动编码  服务端会自动解码
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                data.postValue(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                data.postValue("文件下载成功")

                //将下载的文件保存到本地
                val file=File(Environment.getExternalStorageDirectory().path,"ff.jpg")
                val outputStream=FileOutputStream(file)
                val inputStream =response.body()!!.byteStream()
                val b=ByteArray(1024)
                var i=inputStream.read(b)
                while (i!=-1){
                    outputStream.write(b,0,i)
                    i=inputStream.read(b)
                }
                outputStream.close()
                inputStream.close()

                imagePath.postValue(file.path)
            }
        })
    }


    /**
     * POST上传参数
     */
    fun uploadParmsByPost() {
        val mBody = FormBody.Builder()
            .add("name1","English&中文")
            .add("name2","English&中文")
            .build()

        val request = Request.Builder()
            .url("http://localhost:8079")
            .post(mBody)
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

    /**
     * POST上传带文件的表单
     */
    fun uploadFormByPost() {
        val mFilePath = Environment.getExternalStorageDirectory().path + "/Pictures/d.jpg"
        val mFile = File(mFilePath)
        val mRequestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile)
        val mBody = MultipartBody.Builder()    //OkHttp实现表单上传
            .setType(MultipartBody.FORM)      //setType(MultipartBody.FORM)上传含文件的表单一定要加上
            .addFormDataPart("name0", "test.jpg", mRequestBody) //addFormDataPart()对中文不自动编码
            .addFormDataPart("name1",URLEncoder.encode("English&中文"))  //这个方式上传的字符，Nanohttpd对中文不解码
            .build()


        val request = Request.Builder()
            .url("http://localhost:8079")
            .post(mBody)
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
