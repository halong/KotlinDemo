package com.example.kotlindemo.ui.listview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import org.jetbrains.anko.listView
import org.jetbrains.anko.verticalLayout

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            listView {
                val list = ArrayList<String>()
                var i = 0
                while (i < 30) {
                    list.add("星期四")
                    i++
                }
                adapter = MyAdapter(list)

                onItemClickListener = object : AdapterView.OnItemClickListener {
                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    }

                }
            }
        }
    }
}
