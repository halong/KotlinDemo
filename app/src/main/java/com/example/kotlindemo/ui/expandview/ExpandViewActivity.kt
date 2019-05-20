package com.example.kotlindemo.ui.expandview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_expand_view.*

class ExpandViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_view)

        expand
    }
}
