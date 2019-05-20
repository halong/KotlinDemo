package com.example.kotlindemo.network.nanohttpd.ui.nanohttpd

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Environment
import com.example.kotlindemo.network.nanohttpd.data.nanohttpd.MyNanohttpd
import com.orhanobut.logger.Logger
import okhttp3.*
import okio.Okio
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URLEncoder

class NanohttpdViewModel : ViewModel() {
    private val client = OkHttpClient()

    private val data = MutableLiveData<String>()

    private val imagePath = MutableLiveData<String>()

    private val mNanoHTTPD = MyNanohttpd(8079)

    fun getData(): LiveData<String> = data

    fun getImagePath(): LiveData<String> = imagePath

    fun startServe() {
        mNanoHTTPD.start()
        data.postValue("服务已被开启！")
    }

    fun stopServe() {
        if (mNanoHTTPD.isAlive) {
            mNanoHTTPD.stop()
        }
    }

    /**
     * GET下载字符串
     */
    fun downloadStringByGET() {
        val request = Request.Builder()
            .url("http://localhost:8079?sdgah=中文&daskjd=fjpoooo")    //自动编码
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
     * GET下载文件
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
                val file = File(Environment.getExternalStorageDirectory(), "mySave.jpg")
                val bufferedSink = Okio.buffer(Okio.sink(file))
                bufferedSink.writeAll(response.body()!!.source())
                bufferedSink.flush()  //一定要加上，确保全部数据写入本地

                imagePath.postValue(file.path)
            }
        })
    }


    /**
     * POST上传参数-->FormBody
     */
    fun uploadParmsByPost() {
        val mBody = FormBody.Builder()
            .add("name1", "English&中文")//这里会自动编码  服务端会自动解码
            .add("name2", "English&中文")
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
     * POST上传带文件的表单-->MultipartBody
     */
    fun uploadFormByPost() {
        val mFile = File(Environment.getExternalStorageDirectory().path + "/Pictures/d.jpg")
        val mRequestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile)
        val mBody = MultipartBody.Builder()    //OkHttp实现表单上传
            .setType(MultipartBody.FORM)      //setType(MultipartBody.FORM)上传含文件的表单一定要加上
            .addFormDataPart("name0", "test.jpg", mRequestBody) //addFormDataPart()对中文不自动编码,如果filename包含中文，必须进行编码
            .addFormDataPart("name1", URLEncoder.encode("English&中文", "utf-8"))  //这个方式上传的字符，Nanohttpd对中文不解码
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
     * POST上传带文件的表单带进度监听
     */
    fun uploadFormWithProgress() {
        val mFile = File(Environment.getExternalStorageDirectory().path + "/Pictures/d.jpg")
        val mRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), mFile)
        val mMultipartBody = MultipartBody.Builder()    //OkHttp实现表单上传
            .setType(MultipartBody.FORM)      //setType(MultipartBody.FORM)上传含文件的表单一定要加上
            .addFormDataPart(
                "name",
                URLEncoder.encode(mFile.name,"UTF-8"),
                mRequestBody
            ) //addFormDataPart()对中文不自动编码,如果filename包含中文，必须进行编码
            .build()

        //对MultipartBody进行代理封装
        val mProgressRequestBody = ProgressRequestBody(mMultipartBody) {
            data.postValue(it.toString())
        }

        val request = Request.Builder()
            .url("http://localhost:8079")
            .post(mProgressRequestBody)
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
