package com.example.kotlindemo.ui.listview

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.jetbrains.anko.find
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

/**
 *Created by halong on 2019/5/16
 *@description:
 */
class MyAdapter(private val list: List<String>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = parent?.context?.linearLayout {
            textView(list[position]) {
                textSize = 20f
            }
        }
        return view as View
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}