package com.example.kotlindemo.database.sqlite.ui.sqlite

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.kotlindemo.database.sqlite.data.sqlite.MyDatabaseManager
import com.example.kotlindemo.database.sqlite.data.sqlite.Person

class SqliteViewModel : ViewModel() {
    private val data = MutableLiveData<String>()

    fun getData(): LiveData<String> = data

    fun addPerson(person:Person){
        MyDatabaseManager.addPerson(person)
    }

    fun showPersons(){
        data.postValue(MyDatabaseManager.getPersons().toString())
    }


    fun deletePersons(){
        MyDatabaseManager.deletePersons()
    }

    fun updatePersons(){
        MyDatabaseManager.updatePersons()

    }


}
