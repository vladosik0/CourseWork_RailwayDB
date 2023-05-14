package com.example.coursework.data.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "station")
data class Station(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val stationName:String
)
