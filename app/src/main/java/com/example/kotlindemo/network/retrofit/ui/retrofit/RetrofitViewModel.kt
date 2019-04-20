package com.example.kotlindemo.network.retrofit.ui.retrofit

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.kotlindemo.network.retrofit.data.retrofit.RetrofitUtil
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitViewModel : ViewModel() {
    val messageData:MutableLiveData<String> = MutableLiveData()

    fun getInternetData(user:String){
        //这里enqueue()方法参数引入的是个接口，先用object声明对象，再打出接口名，geenrate->Override Methods
        RetrofitUtil.getGithubApiService().getResponseBody(user).enqueue(object:Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    messageData.value=response!!.body()!!.string()
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

            }
        }

     )
    }
}
