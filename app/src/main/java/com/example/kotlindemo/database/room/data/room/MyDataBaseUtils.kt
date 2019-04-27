package com.example.kotlindemo.database.room.data.room

import android.arch.persistence.room.Room
import com.example.kotlindemo.MyApplication

/**
 *Created by halong on 2019/4/26
 *@description:
 */
object MyDataBaseUtils {
    private val mDatabase=Room.databaseBuilder(MyApplication.getContext(),MyDataBase::class.java,"mydb")
        .allowMainThreadQueries()
        .build()
    val userDao= mDatabase.userDao()
}