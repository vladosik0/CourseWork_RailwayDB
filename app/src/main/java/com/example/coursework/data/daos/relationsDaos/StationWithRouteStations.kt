package com.example.coursework.data.daos.relationsDaos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface StationWithRouteStations {
    @Transaction
    @Query("SELECT * FROM station")
    fun getStationsWithRouteStations(): List<StationWithRouteStations>
}