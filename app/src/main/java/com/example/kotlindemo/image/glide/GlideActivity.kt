package com.example.kotlindemo.image.glide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import org.jetbrains.anko.imageView
import org.jetbrains.anko.verticalLayout

class GlideActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //UI视图
        verticalLayout {
            imageView = imageView()



        }

        Glide.with(this)
            .load("https://gtd.alicdn.com/tfscom/TB1O5eRSHrpK1RjSZTEwu3WAVXa")
//            .placeholder(R.mipmap.ic_launcher)
            .into(imageView)
    }
}
