package com.example.kotlindemo.network.nanohttpd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R
import com.example.kotlindemo.network.nanohttpd.ui.nanohttpd.NanohttpdFragment

class NanohttpdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nanohttpd_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NanohttpdFragment.newInstance())
                .commitNow()
        }
    }

}
