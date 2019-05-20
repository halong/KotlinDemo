package com.example.kotlindemo.anko

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.jetbrains.anko.setContentView

class Anko2Activity : AppCompatActivity() {
    private lateinit var tv: TextView
    private lateinit var viewModel: Anko2ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val anko2UI = Anko2UI()
        anko2UI.setContentView(this)

        tv = anko2UI.getTv()

        viewModel = ViewModelProviders.of(this).get(Anko2ViewModel::class.java)

        viewModel.getText().observe(this, Observer {
            tv.text = it
        })

    }

    fun onClick() {
        viewModel.getNewData()
    }

}
