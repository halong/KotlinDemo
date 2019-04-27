package com.example.kotlindemo.database.room.ui.room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.kotlindemo.database.room.data.room.MyDataBaseUtils
import com.example.kotlindemo.database.room.data.room.User

class RoomViewModel : ViewModel() {
    private val data = MutableLiveData<String>()
    private lateinit var users:List<User>

    fun getData(): LiveData<String> = data

    fun addUser(user: User){
        MyDataBaseUtils.userDao.addUser(user)
    }

    fun getUsers(){
        users=MyDataBaseUtils.userDao.getUsers()
        data.postValue(users.toString())
    }

    fun removeUser(){
        for (user in users){
            MyDataBaseUtils.userDao.removeUser(user)
        }
    }

    fun updateUser(){
        for (user in users){
            user.age=100
            MyDataBaseUtils.userDao.updateUser(user)
        }

    }

}
