package com.example.kotlindemo.database.room.data.room

import android.arch.persistence.room.*

/**
 *Created by halong on 2019/4/26
 *@description:
 */
@Dao
interface UserDao {
    @Query("select * from user")
    fun getUsers():List<User>

    @Query("select * from user where id = :id")
    fun getUserById(id:Int):User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(vararg user: User)

    @Delete
    fun removeUser(vararg user: User)

    @Update
    fun updateUser(vararg user:User)


}