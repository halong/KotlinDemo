package com.example.kotlindemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kotlindemo.database.room.RoomActivity
import com.example.kotlindemo.database.sqlite.SqliteActivity
import com.example.kotlindemo.network.nanohttpd.NanohttpdActivity
import com.example.kotlindemo.network.okhttp.OkhttpActivity
import com.example.kotlindemo.network.retrofit.RetrofitActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn0.setOnClickListener {
            startActivity(Intent(this@MainActivity,OkhttpActivity::class.java))
        }

        btn1.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity,RetrofitActivity::class.java))
        })

        btn2.setOnClickListener {
            startActivity(Intent(this@MainActivity,SqliteActivity::class.java))
        }

        btn3.setOnClickListener {
            startActivity(Intent(this@MainActivity,RoomActivity::class.java))
        }


        btn4.setOnClickListener {
            startActivity(Intent(this@MainActivity,NanohttpdActivity::class.java))
        }
    }
}
