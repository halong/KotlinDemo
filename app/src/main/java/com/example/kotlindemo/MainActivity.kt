package com.example.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.anko.AnkoActivity
import com.example.kotlindemo.database.room.RoomActivity
import com.example.kotlindemo.database.sqlite.SqliteActivity
import com.example.kotlindemo.eventbus.EventBusActivity
import com.example.kotlindemo.handler.HandlerActivity
import com.example.kotlindemo.network.nanohttpd.NanohttpdActivity
import com.example.kotlindemo.network.okhttp.OkhttpActivity
import com.example.kotlindemo.network.okhttputils.OkhttputilsActivity
import com.example.kotlindemo.network.retrofit.RetrofitActivity
import com.example.kotlindemo.network.rxhttp.RxHttpActivity
import com.example.kotlindemo.database.sharedpreferences.SharedPreferencesActivity
import com.example.kotlindemo.image.glide.GlideActivity
import com.example.kotlindemo.ui.listview.ListViewActivity
import com.example.kotlindemo.ui.bounceview.BounceViewActivity
import com.example.kotlindemo.ui.recyclerview.RecyclerViewActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk19.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //使用了Anko扩展库
        scrollView {
            verticalLayout {
                button("handler") {
                    allCaps = false
                    onClick {
                        startActivity<HandlerActivity>()
                    }
                }

                button("nanohttpd") {
                    allCaps = false
                    onClick {
                        startActivity<NanohttpdActivity>()
                    }
                }

                button("retrofit") {
                    allCaps = false
                    onClick {
                        startActivity<RetrofitActivity>()
                    }
                }

                button("Anko") {
                    allCaps = false
                    onClick {
                        startActivity<AnkoActivity>()
                    }
                }

                button("okhttp") {
                    allCaps = false
                    onClick {
                        startActivity<OkhttpActivity>()
                    }
                }


                button("RecyclerView") {
                    allCaps = false
                    onClick {
                        startActivity<RecyclerViewActivity>()
                    }
                }

                button("ListView") {
                    allCaps = false
                    onClick {
                        startActivity<ListViewActivity>()
                    }
                }

                button("OkHttpUtils") {
                    allCaps = false
                    onClick {
                        startActivity<OkhttputilsActivity>()
                    }
                }

                button("SharedPreferences") {
                    allCaps = false
                    onClick {
                        startActivity<SharedPreferencesActivity>()
                    }
                }

                button("Room") {
                    allCaps = false
                    onClick {
                        startActivity<RoomActivity>()
                    }
                }

                button("SQLite") {
                    allCaps = false
                    onClick {
                        startActivity<SqliteActivity>()
                    }
                }

                button("RxHttp") {
                    allCaps = false
                    onClick {
                        startActivity<RxHttpActivity>()
                    }
                }

                button("EventBus") {
                    allCaps = false
                    onClick {
                        startActivity<EventBusActivity>()
                    }
                }

                button("Glide") {
                    allCaps = false
                    onClick {
                        startActivity<GlideActivity>()
                    }
                }

                button("BounceView") {
                    allCaps = false
                    onClick {
                        startActivity<BounceViewActivity>()
                    }
                }


            }
        }


    }
}
