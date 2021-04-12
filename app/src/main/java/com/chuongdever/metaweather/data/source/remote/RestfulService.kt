package com.chuongdever.metaweather.data.source.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestfulService private constructor() {
    private val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getApi(): RestfulApi {
        return Internal.instance.mRetrofit.create(RestfulApi::class.java)
    }

    private object Internal {
        val instance = RestfulService()
    }

    companion object {
        const val BASE_URL = "https://www.metaweather.com"
        val instance = Internal.instance
    }
}