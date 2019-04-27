package com.example.kotlindemo.database.room.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *Created by halong on 2019/4/26
 *@description:
 */
@Entity //实现主键自增的条件：autoGenerate = true;设置主键的缺损值并且实例化时不指定主键值
data class User(@PrimaryKey(autoGenerate = true) var id:Int=0, var  name:String, var age:Int)