package com.example.coursework.data.daos.relationsDaos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.coursework.data.classes.relations.StationWithRouteStations

@Dao
interface StationWithRouteStationsDao {
    @Transaction
    @Query("SELECT * FROM station")
    fun getStationsAndRouteStations(): List<StationWithRouteStations>
}