package com.example.kotlindemo.network.okhttputils

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutCompat
import android.widget.TextView
import com.zhy.http.okhttp.OkHttpUtils
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.linearLayoutCompat
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call
import okhttp3.MediaType
import okio.Okio
import java.io.File
import java.lang.Exception


class OkhttputilsActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private val myTestNanohttpd = MyTestNanohttpd(8080)

    private val myFileNanohttpd = MyFileNanohttpd(8079)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayoutCompat {
            orientation = LinearLayoutCompat.VERTICAL
            lparams {
                width = matchParent
                height = wrapContent
            }

            scrollView {
                lparams {
                    width = matchParent
                    height = 200
                }

                textView = textView {
                }
            }

            button {
                setOnClickListener {
                    get()
                }

                text = "OkHttpUtils.get()"
                isAllCaps = false
            }


            button {
                setOnClickListener {
                    post()
                }

                text = "OkHttpUtils.post()"
                isAllCaps = false
            }

            button {
                setOnClickListener {
                    postFiles()
                }

                text = "OkHttpUtils.postFile()"
                isAllCaps = false
            }

            button {
                setOnClickListener {
                    postString()
                }

                text = "OkHttpUtils.postString()"
                isAllCaps = false
            }

            button {
                setOnClickListener {
                    postFile()
                }

                text = "OkHttpUtils.postFile()"
                isAllCaps = false
            }


        }

        if (!myTestNanohttpd.isAlive) {
            myTestNanohttpd.start()
        }

        if (!myFileNanohttpd.isAlive) {
            myFileNanohttpd.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (myTestNanohttpd.isAlive) {
            myTestNanohttpd.stop()
        }

        if (myFileNanohttpd.isAlive) {
            myFileNanohttpd.stop()
        }
    }

    /**
     * GET请求
     */
    private fun get() {
        doAsync {
            OkHttpUtils
                .get()
                .url("http://localhost:8080")
                .addParams("username", "hyman&中文")
                .addParams("password", "123")
                .build()
                .execute(object : StringCallback() {
                    override fun onError(call: Call?, e: java.lang.Exception?, id: Int) {
                        runOnUiThread {
                            textView.text = e.toString()
                        }
                    }

                    override fun onResponse(response: String?, id: Int) {
                        runOnUiThread {
                            textView.text = response
                        }
                    }
                })
        }
    }


    /**
     * POST请求 上传键值对参数
     */
    private fun post() {
        doAsync {
            OkHttpUtils
                .post()
                .url("http://localhost:8080")
                .addParams("username", "hyman&中文")
                .addParams("password", "123")
                .build()
                .execute(object : StringCallback() {
                    override fun onError(call: Call?, e: java.lang.Exception?, id: Int) {
                        runOnUiThread {
                            textView.text = e.toString()
                        }
                    }

                    override fun onResponse(response: String?, id: Int) {
                        runOnUiThread {
                            textView.text = response
                        }
                    }
                })
        }
    }

    /**
     * Post请求 上传表单形式文件
     */
    private fun postFiles() {
        doAsync {
            val file = File(Environment.getExternalStorageDirectory().path, "/Pictures/d.jpg")
            OkHttpUtils
                .post()
                .addFile("key", "iii.jpg", file)
                .url("http://localhost:8080")
                .build()
                .execute(object : StringCallback() {
                    override fun onError(call: Call?, e: java.lang.Exception?, id: Int) {
                        runOnUiThread {
                            textView.text = e.toString()
                        }
                    }

                    override fun onResponse(response: String?, id: Int) {
                        runOnUiThread {
                            textView.text = response
                        }
                    }
                })
        }
    }


    /**
     * POST请求 上传String
     */
    private fun postString() {
        doAsync {
            OkHttpUtils.postString()
                .url("http://localhost:8080")
                .content("En&中")
                .build().execute(object : StringCallback() {
                    override fun onResponse(response: String?, id: Int) {
                        runOnUiThread {
                            textView.text = response
                        }
                    }

                    override fun onError(call: Call?, e: Exception?, id: Int) {
                        runOnUiThread {
                            textView.text = e.toString()
                        }
                    }

                })
        }
    }


    /**
     * POST上传单个文件
     */
    private fun postFile() {
        doAsync {
            val file = File(Environment.getExternalStorageDirectory(),"original.txt")
            val bs=Okio.buffer(Okio.sink(file))
            bs.write("English&中文".toByteArray())
            bs.flush()

            OkHttpUtils.postFile()
                .url("http://localhost:8079")
                .file(file)
                .build()
                .execute(object : StringCallback() {
                    override fun onResponse(response: String?, id: Int) {
                        runOnUiThread {
                            textView.text = response
                        }
                    }

                    override fun onError(call: Call?, e: Exception?, id: Int) {
                        runOnUiThread {
                            textView.text = e.toString()
                        }
                    }

                })
        }
    }


}
