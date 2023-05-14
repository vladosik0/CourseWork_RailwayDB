package com.example.coursework.data.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "train")
data class Train(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trainNumber: Int,
    val routeId: Int,
    val departureDate: String,
    val arrivalDate: String
)
