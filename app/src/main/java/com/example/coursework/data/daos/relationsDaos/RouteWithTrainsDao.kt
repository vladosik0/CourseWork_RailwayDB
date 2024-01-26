package com.example.coursework.data.daos.relationsDaos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.coursework.data.classes.relations.RouteWithTrains

@Dao
interface RouteWithTrainsDao {
    @Transaction
    @Query("SELECT * FROM train_route")
    fun getRouteAndTrains(): List<RouteWithTrains>
}