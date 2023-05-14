package com.example.coursework.data.repositories

import com.example.coursework.data.classes.Wagon
import kotlinx.coroutines.flow.Flow

interface WagonsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllWagonsStream(): Flow<List<Wagon>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getWagonStream(id: Int): Flow<Wagon?>

    /**
     * Insert item in the data source
     */
    suspend fun insertWagon(item: Wagon)

    /**
     * Delete item from the data source
     */
    suspend fun deleteWagon(item: Wagon)

    /**
     * Update item in the data source
     */
    suspend fun updateWagon(item: Wagon)
}