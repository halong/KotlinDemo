package com.example.kotlindemo.anko

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 *Created by halong on 2019/5/22
 *@description:
 */
class MyManagedSQLiteOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "myDB", null, 1) {
    //单例
    companion object {
        @Volatile //避免指令重排  --原子指令
        private var instance: MyManagedSQLiteOpenHelper? = null

        @Synchronized  //锁定线程
        fun getInstance(ctx: Context)= instance?: MyManagedSQLiteOpenHelper(ctx.applicationContext)
    }



    override fun onCreate(db: SQLiteDatabase?) {
        // Here you create tables
        db?.createTable("Customer", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "name" to TEXT,
            "photo" to BLOB)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db?.dropTable("User", true)
    }
}

// Access property for Context    属性扩展：给Context添加自定义属性  --方法扩展
val Context.database: MyManagedSQLiteOpenHelper
    get() = MyManagedSQLiteOpenHelper.getInstance(this)