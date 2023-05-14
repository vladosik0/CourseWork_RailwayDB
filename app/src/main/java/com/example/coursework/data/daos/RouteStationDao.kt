package com.example.coursework.data.daos

import androidx.room.*
import com.example.coursework.data.classes.RouteStation
import kotlinx.coroutines.flow.Flow

@Dao
interface RouteStationDao {
    @Query("SELECT * from route_station")
    fun getAllItems(): Flow<List<RouteStation>>

    @Query("SELECT * from route_station WHERE id = :id")
    fun getItem(id: Int): Flow<RouteStation>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: RouteStation)

    @Update
    suspend fun update(item: RouteStation)

    @Delete
    suspend fun delete(item: RouteStation)
}