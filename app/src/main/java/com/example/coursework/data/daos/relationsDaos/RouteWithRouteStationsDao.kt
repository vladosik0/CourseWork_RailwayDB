package com.example.coursework.data.daos.relationsDaos

import androidx.room.Query
import androidx.room.Transaction
import com.example.coursework.data.classes.relations.RouteWithRouteStations

interface RouteWithRouteStationsDao {
    @Transaction
    @Query("SELECT * FROM train_route")
    fun getRouteStationsAndRouts(): List<RouteWithRouteStations>
}