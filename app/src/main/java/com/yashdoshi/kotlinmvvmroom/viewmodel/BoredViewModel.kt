package com.yashdoshi.kotlinmvvmroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yashdoshi.kotlinmvvmroom.R
import com.yashdoshi.kotlinmvvmroom.db.ActivityDatabase
import com.yashdoshi.kotlinmvvmroom.db.dao.ActivityDao
import com.yashdoshi.kotlinmvvmroom.db.entity.BoredActivity
import com.yashdoshi.kotlinmvvmroom.repository.BoredRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoredViewModel(val context: Application) : AndroidViewModel(context) {

    private val activityDatabase = ActivityDatabase.getInstannce(context)
    private val boredRepository = BoredRepository(activityDatabase.activityDao())

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean> get() = _isLoading

    val boredActivity : LiveData<List<BoredActivity>> get() =  boredRepository.getAllActivity()
    
    private val _isError : MutableLiveData<String> = MutableLiveData()
    val isError: LiveData<String> get() = _isError

    init {
        getBoredActivity()
    }

    fun getBoredActivity(){
        _isLoading.value = true
        boredRepository.getActivity().enqueue(object : Callback<BoredActivity>{
            override fun onResponse(call: Call<BoredActivity>, response: Response<BoredActivity>) {
                _isLoading.value = false
                if(response.isSuccessful){
                    response.body()?.let { boredRepository.addActivity(it) }
                } else {
                    _isError.value = context.getString(R.string.str_something_wrong)
                }
            }
            
            override fun onFailure(call: Call<BoredActivity>, t: Throwable) {
                _isLoading.value = false
                _isError.value = context.getString(R.string.str_something_wrong)
            }
        })
    }
}