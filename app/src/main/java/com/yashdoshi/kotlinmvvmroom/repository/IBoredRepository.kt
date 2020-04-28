package com.yashdoshi.kotlinmvvmroom.repository

import androidx.lifecycle.LiveData
import com.yashdoshi.kotlinmvvmroom.db.entity.BoredActivity
import retrofit2.Call

interface IBoredRepository {
    fun getActivity(): Call<BoredActivity>
    fun getAllActivity(): LiveData<List<BoredActivity>>
    fun addActivity(activity: BoredActivity)
    fun removeAllActivity()
}