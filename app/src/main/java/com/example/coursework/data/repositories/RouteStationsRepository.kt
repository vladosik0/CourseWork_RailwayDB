package com.example.coursework.data.repositories

import com.example.coursework.data.classes.RouteStation
import kotlinx.coroutines.flow.Flow

interface RouteStationsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllRouteStationsStream(): Flow<List<RouteStation>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getRouteStationStream(id: Int): Flow<RouteStation?>

    /**
     * Insert item in the data source
     */
    suspend fun insertRouteStation(item: RouteStation)

    /**
     * Delete item from the data source
     */
    suspend fun deleteRouteStation(item: RouteStation)

    /**
     * Update item in the data source
     */
    suspend fun updateRouteStation(item: RouteStation)
}