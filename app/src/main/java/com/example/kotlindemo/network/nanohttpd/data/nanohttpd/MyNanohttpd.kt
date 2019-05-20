package com.example.kotlindemo.network.nanohttpd.data.nanohttpd

import android.os.Environment
import android.util.Log
import fi.iki.elonen.NanoHTTPD
import java.io.*
import java.net.URLDecoder
import java.nio.charset.Charset

/**
 * # NanoHTTPD使用总结：
 * 能够接收并响应http请求的服务器端
 *   1,implementation 'com.nanohttpd:nanohttpd:2.2.0'
 *   2,extends NanoHTTPD
 *   3,override serve()
 *
 *Created by halong on 2019/4/22
 *@description:自定义的NanoHTTPD模拟服务端
 * @param port 服务端访问端口 eg:8080 --访问地址：http://localhost:8080
 */
class MyNanohttpd(port: Int) : NanoHTTPD(port) {
    override fun serve(session: IHTTPSession?): Response {
        val sb = StringBuilder()
        when (session!!.method) {
            Method.GET -> {
                session.method

                session.uri

                //获取请求头
                val heads = session.headers
                sb.append("请求头：$heads \n")
//                for (key in heads.keys) {
//                    sb.append(key + ":" + heads[key] + "\n")
//                }

                session.cookies

                session.queryParameterString

                //获取请求参数
                val parms = session.parms//session.parms自动对中文解码
                sb.append("请求参数：$parms \n")

                //NanoHTTPD返回二进制文件流  ** 有模板很重要 **
                if (parms.keys.contains("name") && parms["name"].equals("下载文件")) {
                    val file = File(Environment.getExternalStorageDirectory(), "d.jpg")
                    val data = FileInputStream(file)
                    return newFixedLengthResponse(
                        Response.Status.OK,
                        getMimeTypeForFile(file.path),
                        data,
                        data.available().toLong()
                    )
                }
            }

            Method.POST -> {
                //获取请求头
                val heads = session.headers
                sb.append("请求头：$heads \n")

                //获取请求体  !!!<<对于POST,必须先调用session.parseBody()，再调用session.parms>>
                val body = HashMap<String, String>()
                session.parseBody(body)
                sb.append("请求体（key=文件缓存地址）：$body \n")

                val parms = session.parms //对于POST,必须先调用session.parseBody()，再调用session.parms
                sb.append("请求体（key=文件名）：" + "\n")
                //如果上传的是表单--包括键值对表单 键-文件名-文件表单；Body为空或key=文件缓存地址
                //如果上传的不是表单，则Body为 postData=...   parms为空
                for (key in parms.keys) {
                    if (body[key] == null) { //说明上传的是带字符串的表单  不会自动对中文解码
                        sb.append(key + "---" + URLDecoder.decode(parms[key], "utf-8") + "\n")
                    } else {    //说明上传的是带文件的表单
                        sb.append(key + "---" + parms[key] + "\n")
                        //将接收的文件存入本地
                        val mFile = File(Environment.getExternalStorageDirectory(), parms[key])
                        val mOutputStream = FileOutputStream(mFile)
                        val tmpFile = File(body[key])
                        val tmpInputStream = FileInputStream(tmpFile)
                        val b = ByteArray(1024 * 8)
                        var i = tmpInputStream.read(b)
                        while (i != -1) {
                            mOutputStream.write(b, 0, i)
                            i = tmpInputStream.read(b)
                        }
                        tmpInputStream.close()
                        mOutputStream.close()
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