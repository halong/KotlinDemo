package com.example.kotlindemo.network.okhttputils

import android.os.Environment
import fi.iki.elonen.NanoHTTPD
import okio.Okio
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset

/**
 *Created by halong on 2019/5/11
 *@description:
 */
class MyFileNanohttpd(port: Int) : NanoHTTPD(port) {
    override fun serve(session: IHTTPSession?): Response {
        val files = HashMap<String, String>()
        session!!.parseBody(files)

        val file = File(Environment.getExternalStorageDirectory(), "ll")
        val outputStream = FileOutputStream(file)
        outputStream.write(files.get("postData")!!.toByteArray(Charset.forName("UTF-8")))
        outputStream.flush()

        return newFixedLengthResponse(files.get("postData")!!)
    }
}