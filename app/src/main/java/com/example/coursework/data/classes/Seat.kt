package com.example.coursework.data.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seat")
data class Seat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val seatNumber: Int,
    val wagonId: Int
)
