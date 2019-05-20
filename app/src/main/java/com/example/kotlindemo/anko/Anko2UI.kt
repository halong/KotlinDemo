package com.example.kotlindemo.anko

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk19.coroutines.onClick
import java.lang.ref.WeakReference

/**
 *Created by halong on 2019/5/13
 *@description:
 */
class Anko2UI : AnkoComponent<Anko2Activity> {
    private lateinit var tv: TextView

    fun getTv() = tv

    override fun createView(ui: AnkoContext<Anko2Activity>) = ui.apply {
        verticalLayout {
            val anko2Activity=ui.owner

            tv = textView("Hello, I am anko layout.")

            button("Change the words.") {
                onClick {
                    anko2Activity.onClick()

                }
            }
        }
    }.view


}