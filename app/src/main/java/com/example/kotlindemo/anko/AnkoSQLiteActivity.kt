package com.example.kotlindemo.anko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class AnkoSQLiteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database.use {

        }
    }
}
