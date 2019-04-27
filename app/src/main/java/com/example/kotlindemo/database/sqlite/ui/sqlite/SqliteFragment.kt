package com.example.kotlindemo.database.sqlite.ui.sqlite

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindemo.R
import com.example.kotlindemo.database.sqlite.data.sqlite.Person
import kotlinx.android.synthetic.main.sqlite_fragment.*

class SqliteFragment : Fragment() {

    companion object {
        fun newInstance() = SqliteFragment()
    }

    private lateinit var viewModel: SqliteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.sqlite_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SqliteViewModel::class.java)

        viewModel.getData().observe(this, Observer {
            text.text=it
        })


        btn0.setOnClickListener {
            viewModel.addPerson(Person("lilei",24))
        }

        btn1.setOnClickListener {
            viewModel.showPersons()
        }

        btn2.setOnClickListener {
            viewModel.deletePersons()
        }

        btn3.setOnClickListener {
            viewModel.updatePersons()
        }
    }

}
