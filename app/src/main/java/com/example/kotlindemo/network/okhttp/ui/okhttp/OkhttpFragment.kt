package com.example.kotlindemo.network.okhttp.ui.okhttp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.okhttp_fragment.*

class OkhttpFragment : Fragment() {

    companion object {
        fun newInstance() = OkhttpFragment()
    }

    private lateinit var viewModel: OkhttpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.okhttp_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OkhttpViewModel::class.java)

        //TextView binds textData
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                text.text=it
            }
        })

        btn0.setOnClickListener {
            viewModel.getHTMLString()
        }

        btn1.setOnClickListener {
            viewModel.downloadFile()
        }

    }

}
