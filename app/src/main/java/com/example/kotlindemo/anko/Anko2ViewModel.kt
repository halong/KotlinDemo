package com.example.kotlindemo.anko

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

/**
 *Created by halong on 2019/5/14
 *@description:
 */
class Anko2ViewModel(application: Application) : AndroidViewModel(application) {
    private val text = MutableLiveData<String>()

    private var i = 0

    fun getText(): LiveData<String> = text

    fun getNewData() {
        i++
        text.value = "点击了$i 次"
    }


    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        super.onCleared()
    }
}