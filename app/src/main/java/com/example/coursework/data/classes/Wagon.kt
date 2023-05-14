package com.example.coursework.data.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wagon")
data class Wagon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val wagonNumber: Int,
    val wagonType: String,
    val trainId: Int
)
