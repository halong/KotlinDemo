package com.example.kotlindemo.network.nanohttpd.ui.nanohttpd

import android.os.Environment
import com.orhanobut.logger.Logger
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

/**
 *Created by halong on 2019/5/6
 *@description:带有上传进度监听的RequestBody
 *
 * 代理模式封装
 */
class ProgressRequestBody(private val body: RequestBody, private val progress: (Int) -> Unit) : RequestBody() {
    private val contentLength = body.contentLength()

    override fun contentType(): MediaType? {
        return body.contentType()
    }

    override fun contentLength(): Long {
        return contentLength
    }

    override fun writeTo(sink: BufferedSink) {
        //代理封装 (Sink)->Sink
        val forwardingSink: Sink = object : ForwardingSink(sink) {
            var totalByteCount = 0L

            override fun write(source: Buffer, byteCount: Long) {
                totalByteCount += byteCount
                progress((totalByteCount * 100 / contentLength).toInt())
                super.write(source, byteCount)
            }
        }
        body.writeTo(Okio.buffer(forwardingSink))
        forwardingSink.flush()

    }

}