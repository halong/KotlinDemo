package com.example.kotlindemo.anko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk19.coroutines.onClick

/**
 * Anko地址：https://github.com/Kotlin/anko
 * Anko:kotlin对Andoid的简化扩展库
 * Anko简化扩展的地方：
 *      Commoms--Intents,Dialog,Toast,Logging,Resource,dimension
 *      Layouts
 *      SQLite
 *      Coroutines
 *
 *
 *
 */

class AnkoActivity : AppCompatActivity(),AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        info("London is the capital of Great Britain")

        verticalLayout {
            val textView = textView("Hello,I am anko.")
                .lparams {
                    width = matchParent
                    height = wrapContent
                }

            button("start another activity") {
                allCaps = false
                onClick {
                    startActivity<Anko2Activity>()
                }
            }

        }
    }
}
