package com.example.kotlindemo.network.nanohttpd.ui.nanohttpd

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.nanohttpd_fragment.*
import java.io.File

class NanohttpdFragment : Fragment() {

    companion object {
        fun newInstance() = NanohttpdFragment()
    }

    private lateinit var viewModel: NanohttpdViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.nanohttpd_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NanohttpdViewModel::class.java)

        viewModel.getData().observe(this, Observer {
            text.text=it
        })

        viewModel.getImagePath().observe(this, Observer {
            image.setImageURI(Uri.fromFile(File(it)))
        })

        btn0.setOnClickListener {
            viewModel.startServe()
        }

        btn1.setOnClickListener {
            viewModel.downloadStringByGET()
        }

        btn2.setOnClickListener {
            viewModel.downloadFileByGET()
        }

        btn3.setOnClickListener {
            viewModel.uploadParmsByPost()
        }

        btn4.setOnClickListener {
            viewModel.uploadFormByPost()
        }

        btn5.setOnClickListener {
            viewModel.stopServe()
        }
    }

}
