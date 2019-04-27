package com.example.kotlindemo.database.room

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R
import com.example.kotlindemo.database.room.ui.room.RoomFragment

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RoomFragment.newInstance())
                .commitNow()
        }
    }

}
