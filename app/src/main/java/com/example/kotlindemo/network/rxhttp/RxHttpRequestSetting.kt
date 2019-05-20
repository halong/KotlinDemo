package com.example.kotlindemo.network.rxhttp

import per.goweii.rxhttp.request.setting.DefaultRequestSetting

/**
 *Created by halong on 2019/5/18
 *@description:
 */
class RxHttpRequestSetting: DefaultRequestSetting() {
    override fun getBaseUrl(): String {
        return Config.Base_Url
    }

    override fun getSuccessCode(): Int {
        return 200
    }
}