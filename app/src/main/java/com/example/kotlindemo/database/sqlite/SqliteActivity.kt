package com.example.kotlindemo.database.sqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R
import com.example.kotlindemo.database.sqlite.ui.sqlite.SqliteFragment

class SqliteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sqlite_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SqliteFragment.newInstance())
                .commitNow()
        }
    }

}
