package com.yashdoshi.kotlinmvvmroom.repository

import androidx.lifecycle.LiveData
import com.yashdoshi.kotlinmvvmroom.db.dao.ActivityDao
import com.yashdoshi.kotlinmvvmroom.retrofit.RestClient
import com.yashdoshi.kotlinmvvmroom.db.entity.BoredActivity
import retrofit2.Call

class BoredRepository(private val activityDao: ActivityDao) : IBoredRepository {

    private val restClient = RestClient()

    override fun getActivity(): Call<BoredActivity> {
        return restClient.apiInterface.getActivity()
    }

    override fun getAllActivity() : LiveData<List<BoredActivity>>{
        return  activityDao.getALlActivity()
    }

    override fun addActivity(activity: BoredActivity){
        activityDao.add(activity)
    }

    override fun removeAllActivity(){
        activityDao.deleteAllActivity()
    }
}