package com.yashdoshi.kotlinmvvmroom.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yashdoshi.kotlinmvvmroom.retrofit.interfaces.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {

    private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val gson: Gson by lazy { GsonBuilder().serializeNulls().create() }
    private val timeOutInterval = 60L
    var okHttpClient: OkHttpClient
    val apiInterface: ApiInterface by lazy { getMyApiInterface() }

    init {
        okHttpClientBuilder.connectTimeout(timeOutInterval, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(timeOutInterval, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(timeOutInterval, TimeUnit.SECONDS)
        okHttpClient = okHttpClientBuilder.build()
    }

    private fun getMyApiInterface(): ApiInterface {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(ApiInterface.BASE_URL)
        retrofitBuilder.client(okHttpClientBuilder.build())
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
        val retrofit = retrofitBuilder.build()
        return retrofit.create(ApiInterface::class.java)
    }
}