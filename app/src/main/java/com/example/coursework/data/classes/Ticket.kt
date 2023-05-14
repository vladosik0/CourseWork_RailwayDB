package com.example.coursework.data.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket")
data class Ticket(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val price: Double,
    val available: Boolean = true,
    val startStationId: Int,
    val endStationId: Int,
    val seatId:Int
)
