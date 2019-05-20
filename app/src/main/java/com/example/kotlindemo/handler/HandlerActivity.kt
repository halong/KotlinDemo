package com.example.kotlindemo.handler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import com.orhanobut.logger.Logger
import org.jetbrains.anko.button
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk19.coroutines.onClick
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class HandlerActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            textView = textView {
                text = "Hello"
            }

            button("开启子线程") {
                onClick {
                    MyTest{
                        textView.text=it.toString()
                }
            }
        }
    }


    }


}
