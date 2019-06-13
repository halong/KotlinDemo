package com.example.kotlindemo.eventbus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.TextView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk19.coroutines.onClick
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import java.net.URI
import java.net.URL

/**
 * EventBus仓库地址：https://github.com/greenrobot/EventBus
 *
 *
 */

class EventBusActivity : AppCompatActivity() {
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //UI视图
        verticalLayout {
            tv = textView("Hello,EventBus")

            button("开启协程") {
                var count = 0
                val message = Message()
                onClick {
                    launch {//协程没有另开线程，还在主线程，可以挂起，但是不能做延时操作
                       while (count<10){
                           count++
                           message.what = count
//                           val html=URL("http://www.baidu.com").readText()
                           //NetworkOnMainThreadException
                           message.obj = Thread.currentThread().name
                           delay(1000L)
                           EventBus.getDefault().post(message)  //利用EventBus发送消息
                       }
                    }
                    isClickable=false
                }

            }

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)  //订阅EventBus
    fun onEventMessage(message: Message) {  //参数类型，方法名都可以自定义
        tv.text = "点击了${message.what}次,${message.obj as String} "

    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)   //注册EventBus
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)   //注销EventBus
    }

}
