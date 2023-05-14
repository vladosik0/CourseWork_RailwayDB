package com.example.coursework.data.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "train_route")
data class TrainRoute(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val routeName: String
)