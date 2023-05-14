package com.example.coursework.data.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "route_station")
data class RouteStation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val routeSerialNumber: Int,
    val stationId: Int,
    val routeId: Int
    )