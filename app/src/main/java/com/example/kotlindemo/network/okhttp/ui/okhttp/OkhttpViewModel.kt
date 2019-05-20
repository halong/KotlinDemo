package com.example.kotlindemo.network.okhttp.ui.okhttp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Environment
import com.example.kotlindemo.network.okhttp.data.okhttp.MyOkHttpUtils
import com.example.kotlindemo.network.okhttp.data.okhttp.ProgressListener
import okhttp3.*
import okio.Okio
import java.io.*


class OkhttpViewModel : ViewModel() {
    private val mOkHttpClient = OkHttpClient.Builder()
        .build()

    private val data: MutableLiveData<String> = MutableLiveData()

    fun getData(): LiveData<String> = data

    fun download() {
        MyOkHttpUtils.download("https://www.uc123.com/", object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                data.postValue(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                data.postValue(response.toString())
            }

        })
    }


    fun downloadFile() {
        val url = "https://gtd.alicdn.com/tfscom/TB1OySRSHrpK1RjSZTEwu3WAVXa"
        val request = Request.Builder()
            .url(url)
            .build()
        mOkHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                data.postValue(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                data.postValue("文件下载成功")

                //将下载的文件保存到本地
                val mFile = File(Environment.getExternalStorageDirectory(), getNameFormURL(url))
                val sink = Okio.buffer(Okio.sink(mFile))   //这个是Okio输出流写法
                sink.writeAll(response.body()!!.source())
                sink.flush()

                //这个是输入流写法
//                val string:String=Okio.buffer(Okio.source(mFile)).readString(Charset.forName("UTF-8"))


//                val mInputStream = response.body()!!.byteStream()
//                val mFile = File(Environment.getExternalStorageDirectory(),getNameFormURL(url))
//                val mOutputStream = FileOutputStream(mFile)
//                val b = ByteArray(1024)
//                var i = mInputStream.read(b)
//                while (i != -1) {
//                    mOutputStream.write(b, 0, i)
//                    i = mInputStream.read(b)
//                }
//                mOutputStream.close()
//                mInputStream.close()
            }
        })
    }


    fun downloadFileWithProgress() {
        val url = "http://sjws.ssl.qihucdn.com/mobile/shouji360/360safesis/20190415-1021/360MobileSafe_8.1.0.1031.apk"
        val listener = object : ProgressListener {
            override fun onSuccess(response: Response) {
                data.postValue(response.toString())
            }

            override fun onFailure(e: IOException) {
                data.postValue(e.localizedMessage)
            }

            override fun onProgress(percent: Int) {
                data.postValue(percent.toString())
            }
        }
        MyOkHttpUtils.downloadFileWithProgress(url, Environment.getExternalStorageDirectory(), listener)
    }


    fun getNameFormURL(url: String): String {
        val string = url.trimEnd(' ', '/')
        val st = string.split("/")
        if (!st.isEmpty())
            return st[st.size - 1]
        else
            return url
    }
}
