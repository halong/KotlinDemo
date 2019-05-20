package com.example.kotlindemo.database.room.ui.room

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindemo.R
import com.example.kotlindemo.database.room.data.room.User
import kotlinx.android.synthetic.main.room_fragment.*

class RoomFragment : Fragment() {

    companion object {
        fun newInstance() = RoomFragment()
    }

    private lateinit var viewModel: RoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.room_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)

        viewModel.getData().observe(this, Observer {
            text.text=it
        })

        btn0.setOnClickListener {
            viewModel.addUser(User(name="lilei",age=20))  //函数调用时使用默认值的例子
            //实现主键自增的3个条件，entity--autoGenerate = true;data class constructor primary key default;实例化时不指定主键
        }


        btn1.setOnClickListener {
            viewModel.getUsers()
        }

        btn2.setOnClickListener {
            viewModel.removeUser()
        }

        btn3.setOnClickListener {
            viewModel.updateUser()
        }
    }

}
