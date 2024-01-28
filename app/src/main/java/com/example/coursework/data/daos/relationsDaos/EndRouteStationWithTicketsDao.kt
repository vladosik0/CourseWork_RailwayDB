package com.example.coursework.data.daos.relationsDaos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.coursework.data.classes.relations.EndRouteStationWithTickets

@Dao
interface EndRouteStationWithTicketsDao {
    @Transaction
    @Query("SELECT * FROM route_station")
    fun getEndRouteStationsAndTickets(): List<EndRouteStationWithTickets>
}