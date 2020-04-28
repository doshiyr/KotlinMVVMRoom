package com.yashdoshi.kotlinmvvmroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yashdoshi.kotlinmvvmroom.db.dao.ActivityDao
import com.yashdoshi.kotlinmvvmroom.db.entity.BoredActivity

@Database(entities = [BoredActivity::class], version = 1)
abstract class ActivityDatabase : RoomDatabase(){

    abstract fun activityDao() : ActivityDao

    companion object{
        private var instance: ActivityDatabase? = null
        fun getInstannce(context: Context): ActivityDatabase {
            if(instance == null){
                synchronized(ActivityDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        ActivityDatabase::class.java, "activity_database.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }

    }
}