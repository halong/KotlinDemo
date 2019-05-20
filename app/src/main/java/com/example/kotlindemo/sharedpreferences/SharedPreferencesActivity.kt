package com.example.kotlindemo.sharedpreferences

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk19.coroutines.onClick

class SharedPreferencesActivity : AppCompatActivity() {
    private lateinit var tv:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            tv = textView(defaultSharedPreferences.getString("key", "defValue"))

            button {
                var i=0
                onClick {
                    i++
                    defaultSharedPreferences.edit().putString("key", "点击了$i 下").apply()
                }
            }
        }

        defaultSharedPreferences.edit().putString("key", "你好").apply()

        defaultSharedPreferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            tv.text = sharedPreferences.getString("key", "default_value")
        }

    }
}
