package com.example.kotlindemo.database.sqlite.data.sqlite

import com.example.kotlindemo.MyApplication

/**
 *Created by halong on 2019/4/25
 *@description:
 */
object MyDatabaseManager {
    private val mySqliteOpenHelper = MySqliteOpenHelper(MyApplication.getContext())
    private val db = mySqliteOpenHelper.writableDatabase

    fun addPerson(person: Person) {
        val sql = "insert into person values(null,\"${person.name}\",${person.age})"
        //这里需要注意name是String，要加引号
        db.beginTransaction()
        try {
            db.execSQL(sql)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun getPersons(): List<Person> {

        val persons = ArrayList<Person>()
//        val cursor = db.rawQuery("select * from person", null)
        //初始化Array使用arrayOf()
        //Array(3,"name") stands for 3个“name”组成的数组
        val cursor=db.query("person",arrayOf("_id","name","age"),null,null,null,null,null)
        //查询使用rawQuery()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val age = cursor.getInt(cursor.getColumnIndex("age"))
            val person = Person(id, name, age)
            persons.add(person)
        }
        cursor.close()
        return persons
    }

    fun deletePersons() {
        db.execSQL("delete from person")
//        db.delete("person", "age > ?", Array(1) { "0" })
    }

    fun updatePersons() {
        db.execSQL("update person set age = 100")
//        val cv = ContentValues()
//        cv.put("age",100)
//        db.update("person",cv,null,null)
    }
}