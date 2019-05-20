package com.example.kotlindemo.network.okhttp.data.okhttp

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import java.io.IOException

/**
 *Created by halong on 2019/5/3
 *@description: 自定义ResponseBody  装饰模式-- 继承ResponseBody，又传入参数ResponseBody
 */
class ProgressResponseBody(private val body: ResponseBody, private val progress: (Int) -> Unit) : ResponseBody() {
    private var bufferedSource: BufferedSource? = null

    private val contentLength = body.contentLength()

    override fun contentLength(): Long {
        return contentLength
    }

    override fun contentType(): MediaType? {
        return body.contentType()
    }

    override fun source(): BufferedSource {
        if (bufferedSource == null) {   //避免重复新建
            bufferedSource = Okio.buffer(source(body.source()))
        }
        return bufferedSource!!
    }

    // change source to source
    private fun source(source: Source): Source {
        return object : ForwardingSource(source) {     //关键在这里ForwardingSource(source)
            var totalBytesRead = 0L
            //这里会被重复回调 -- 每一段读取的长度
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)

                totalBytesRead += if (bytesRead != -1L) bytesRead else 0

                progress((totalBytesRead * 100 / contentLength).toInt())

                return bytesRead
            }
        }
    }
}