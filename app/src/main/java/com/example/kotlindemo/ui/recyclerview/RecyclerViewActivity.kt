package com.example.kotlindemo.ui.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            val list = ArrayList<String>()
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")
            list.add("中国")

            recyclerView {
                layoutManager=LinearLayoutManager(this@RecyclerViewActivity)
                adapter = MyAdapter(list)
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}
