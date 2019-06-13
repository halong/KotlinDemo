package com.example.kotlindemo.image.glide

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import org.jetbrains.anko.imageView
import org.jetbrains.anko.verticalLayout

/**
 *Created by halong on 2019/5/21
 *@description:
 */
class MyAdapter(private val list: List<String>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var imageView: ImageView? = null
        val itemView = parent!!.verticalLayout {
            imageView = imageView()
        }

        Glide.with(parent)
            .load(list[position])
            .centerCrop()
            .into(imageView!!)

        return itemView
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