package com.example.kotlindemo.database.sqlite.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 *Created by halong on 2019/4/25
 *@description:SQLite数据库
 */

class MySqliteOpenHelper(context:Context):SQLiteOpenHelper(context,"mydb.db",null,1){
    //数据库第一次被创建时onCreate会被调用
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("Create table if not exists person(_id integer primary key autoincrement,name varchar,age integer) ")
    }


    //如果VERSION值被改为2,系统发现现有数据库版本不同时会调用onUpgrade() 不再调用onCreate()
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}