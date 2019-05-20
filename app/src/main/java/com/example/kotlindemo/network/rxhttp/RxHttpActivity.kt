package com.example.kotlindemo.network.rxhttp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*
import per.goweii.rxhttp.core.RxHttp

/**
 *RxHttp扩展库： https://github.com/goweii/RxHttp
 */
class RxHttpActivity : AppCompatActivity() {
    private val myTestNanohttpd=MyTestNanohttpd(8080)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        verticalLayout {
            val tv = textView("hello").lparams {
                width = matchParent
                height = dip(200)
            }

            button("GET请求") {


            }

        }


        if (!myTestNanohttpd.isAlive){
            myTestNanohttpd.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (myTestNanohttpd.isAlive){
            myTestNanohttpd.stop()
        }
    }
}
