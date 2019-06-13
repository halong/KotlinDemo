package com.example.kotlindemo.ui.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kotlindemo.R

/**
 *Created by halong on 2019/5/16
 *@description:
 */
class MyAdapter(private val list: List<String>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_list_view, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder   //view返回后就成了convertView
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.textView.text = list[position]

        return view
    }

    inner class ViewHolder(itemView: View) {
        val textView: TextView = itemView.findViewById(R.id.tv)
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