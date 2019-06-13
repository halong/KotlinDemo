package com.example.kotlindemo.ui.bounceview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.example.kotlindemo.R
import hari.bounceview.BounceView
import kotlinx.android.synthetic.main.activity_bounce_view.*

/**
 * ## Bounceview-Android地址:
 * https://github.com/hariprasanths/Bounceview-Android
 *
 * ### Bounceview-Android定义：
 * 可以给任意View添加自定义响应动画的扩展库
 *
 *
 *
 */
class BounceViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bounce_view)

        BounceView.addAnimTo(btn)
    }


    fun showDialog(view:View){
        val dialog= AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher)
            .setCancelable(true)
            .setTitle("Title.")
            .setMessage("Message.Message.Message.Message.Message.Message.")
            .create()
        BounceView.addAnimTo(dialog)
        dialog.show()
    }


}
