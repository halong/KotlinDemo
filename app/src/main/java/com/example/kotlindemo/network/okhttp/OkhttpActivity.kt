package com.example.kotlindemo.network.okhttp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R
import com.example.kotlindemo.network.okhttp.ui.okhttp.OkhttpFragment

class OkhttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.okhttp_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OkhttpFragment.newInstance())
                .commitNow()
        }
    }

}
