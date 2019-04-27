package com.example.kotlindemo.database.room.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 *Created by halong on 2019/4/26
 *@description:
 */

@Database(entities = arrayOf(User::class),version = 1,exportSchema = false)
abstract class MyDataBase:RoomDatabase() {
    abstract fun userDao():UserDao
}