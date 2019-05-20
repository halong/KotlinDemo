package com.example.kotlindemo.ui.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.orhanobut.logger.Logger
import org.jetbrains.anko.*

/**
 *Created by halong on 2019/5/15
 *@description:
 */
class MyAdapter(private val list: List<String>) : RecyclerView.Adapter<MyAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val view = parent.context.verticalLayout {
            textView {
                id = Ids.ID_OK
                textSize = 20f
            }
        }

        return VH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textView.text = list[position]
    }

    object Ids {
        val ID_OK = 1
    }


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.find<TextView>(Ids.ID_OK)

    }
}