package com.example.kotlindemo.network.retrofit.ui.retrofit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindemo.R
import com.example.kotlindemo.network.retrofit.data.retrofit.RetrofitUtil
import kotlinx.android.synthetic.main.retrofit_fragment.*

class RetrofitFragment : Fragment() {

    companion object {
        fun newInstance() = RetrofitFragment()
    }

    private lateinit var viewModel: RetrofitViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.retrofit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RetrofitViewModel::class.java)

        viewModel.messageData.observe(this, Observer {
            if (it != null) {
                text.text = it
            }
        })

        btn0.setOnClickListener(View.OnClickListener {
            viewModel.getInternetData("halong")
        })
    }

}
