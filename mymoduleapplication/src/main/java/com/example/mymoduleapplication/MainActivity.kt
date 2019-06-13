package com.example.mymoduleapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            //library activity不要与application activity重名
            //startActivity(Intent(this,com.example.mymodulelibrary.MainActivity::class.java))  //不发生跳转
            startActivity(Intent(this,com.example.mymodulelibrary.Main3Activity::class.java))
        }

    }
}
