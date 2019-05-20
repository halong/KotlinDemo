package com.example.kotlindemo.network.okhttputils

import android.os.Environment
import android.util.Log
import fi.iki.elonen.NanoHTTPD
import okio.Okio
import java.io.*
import java.net.URLDecoder
import java.nio.charset.Charset

/**
 *Created by halong on 2019/4/22
 *@description:自定义的NanoHTTPD模拟服务端
 * @param port 服务端访问端口 eg:8080 --访问地址：http://localhost:8080
 */
class MyTestNanohttpd(port: Int) : NanoHTTPD(port) {
    override fun serve(session: IHTTPSession?): Response {
        val sb = StringBuilder()
        when (session!!.method) {
            Method.GET -> {
                //获取请求头
                val heads = session.headers
                sb.append("请求头：$heads \n")

                //获取请求参数
                val parms = session.parms//session.parms自动对中文解码
                sb.append("请求参数：$parms \n")
            }

            Method.POST -> {
                //获取请求头
                val heads = session.headers
                sb.append("请求头：$heads \n")

                //获取请求体  !!!<<对于POST,必须先调用session.parseBody()，再调用session.parms>>
                val files = HashMap<String, String>()
                session.parseBody(files)
                sb.append("请求体（key--文件缓存地址）：$files \n")

                val parms = session.parms //对于POST,必须先调用session.parseBody()，再调用session.parms
                sb.append("请求体（key--文件名）：\n")
                for (key in parms.keys) {
                    if (files[key] == null) { //说明上传的是带字符串的表单  不会自动对中文解码
                        sb.append(key + "---" + URLDecoder.decode(parms[key], "utf-8") + "\n")
                    } else {    //说明上传的是带文件的表单
                        sb.append(key + "---" + parms[key] + "\n")
                        //将接收的文件存入本地
                        val mFile = File(Environment.getExternalStorageDirectory(), parms[key])
                        val tmpFile = File(files[key])

                        val bufferSink = Okio.buffer(Okio.sink(mFile))
                        val source = Okio.source(tmpFile)
                        bufferSink.writeAll(source)
                        bufferSink.flush()
                    }

                }
            }

            else -> {

            }
        }

        //NanoHTTPD返回字符串
        return newFixedLengthResponse(
            "NanoHTTPD服务端成功响应！" +
                    "\n 端口号：${listeningPort}" +
                    "\n访问方式：${session.method}" +
                    "\n$sb"
        )
    }
}