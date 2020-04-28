package com.yashdoshi.kotlinmvvmroom.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "activity_tabel")
data class BoredActivity(
    @SerializedName("accessibility")
    var accessibility: Double,
    @SerializedName("activity")
    var activity: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("key")
    var key: String,
    @SerializedName("link")
    var link: String,
    @SerializedName("participants")
    var participants: Int,
    @SerializedName("price")
    var price: Double,
    @SerializedName("type")
    var type: String
)