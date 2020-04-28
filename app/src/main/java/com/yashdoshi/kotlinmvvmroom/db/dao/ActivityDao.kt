package com.yashdoshi.kotlinmvvmroom.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yashdoshi.kotlinmvvmroom.db.entity.BoredActivity

@Dao
interface ActivityDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(boredActivity: BoredActivity)

    @Query("DELETE FROM activity_tabel")
    fun deleteAllActivity()

    @Query("SELECT * FROM activity_tabel")
    fun getALlActivity() : LiveData<List<BoredActivity>>

}