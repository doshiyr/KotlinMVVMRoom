package com.yashdoshi.kotlinmvvmroom.retrofit.interfaces

import com.yashdoshi.kotlinmvvmroom.db.entity.BoredActivity
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    companion object{
        const val BASE_URL = "https://www.boredapi.com/api/"
    }

    @GET("activity/")
    fun getActivity() : Call<BoredActivity>

}