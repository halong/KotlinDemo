package com.example.kotlindemo.database.sqlite.data.sqlite

/**
 *Created by halong on 2019/4/25
 *@description:
 */
data class Person (var _id:Int=0,var name:String,var age:Int){
    constructor(name: String,age: Int):this(0,name,age)
}