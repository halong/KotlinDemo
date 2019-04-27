package com.example.kotlindemo.network.nanohttpd.data.nanohttpd

import android.os.Environment
import android.util.Log
import fi.iki.elonen.NanoHTTPD
import java.io.*
import java.net.URLDecoder
import java.nio.charset.Charset

/**
 *Created by halong on 2019/4/22
 *@description:
 */
class MyNanohttpd(port: Int) : NanoHTTPD(port) {

    override fun serve(session: IHTTPSession?): Response {
        var string = ""
        when (session!!.method) {
            Method.GET -> {
                //获取请求头
                val heads = session.headers
                string += ("请求头：" + "\n")
                for (key in heads.keys) {
                    string += (key + ":" + heads[key] + "\n")
                }

                //获取请求参数
                val parms = session.parms//session.parms自动对中文解码
                string += ("请求参数：" + "\n")
                for (key in parms.keys) {
                    string += (key + ":" + parms[key] + "\n")
                }

            }

            Method.POST -> {
                //获取请求头
                val heads = session.headers
                string += ("请求头：" + "\n")
                for (key in heads.keys) {
                    string += (key + ":" + heads[key] + "\n")
                }

                //获取请求体
                val files = HashMap<String, String>()
                session.parseBody(files)
                string += ("请求体（key--文件缓存地址）：" + "\n")
                for (key in files.keys) {
                    string += (key + "---" + files[key]+"\n")
                }

                val parms=session.parms //必须先调用session.parseBody()，再调用session.parms
                string += ("请求体（key--文件名）：" + "\n")
                for (key in parms.keys){
                    string += (key + "---" + parms[key]+"\n")

                    //将接收的文件存入本地
                    val mFilePath=Environment.getExternalStorageDirectory().path+"/"+parms[key]
                    val mFile = File(mFilePath)
                    val mOutputStream=FileOutputStream(mFile)

                    val tmpFile=File(files[key])
                    val tmpInputStream=FileInputStream(tmpFile)

                    val b=ByteArray(tmpInputStream.available())
                    tmpInputStream.read(b)
                    mOutputStream.write(b)

                    tmpInputStream.close()
                    mOutputStream.close()

                }
            }

            else -> {

            }

        }

        return newFixedLengthResponse(
            "NanoHTTPD服务端成功响应！" +
                    "\n 端口号：${listeningPort}" +
                    "\n访问方式：${session.method}" +
                    "\n$string"
        )
    }
}