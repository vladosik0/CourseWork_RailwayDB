package com.example.coursework.data.daos.relationsDaos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.coursework.data.classes.relations.StartRouteStationWithTickets

@Dao
interface StartRouteStationWithTicketsDao {
    @Transaction
    @Query("SELECT * FROM route_station")
    fun getStartRouteStationsAndTickets(): List<StartRouteStationWithTickets>
}