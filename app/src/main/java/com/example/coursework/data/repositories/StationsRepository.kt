package com.example.coursework.data.repositories

import com.example.coursework.data.classes.Station
import kotlinx.coroutines.flow.Flow

interface StationsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllStationsStream(): Flow<List<Station>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getStationStream(id: Int): Flow<Station?>

    /**
     * Insert item in the data source
     */
    suspend fun insertStation(item: Station)

    /**
     * Delete item from the data source
     */
    suspend fun deleteStation(item: Station)

    /**
     * Update item in the data source
     */
    suspend fun updateStation(item: Station)
}