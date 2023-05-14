package com.example.coursework.data.repositories

import com.example.coursework.data.classes.Seat
import kotlinx.coroutines.flow.Flow

interface SeatsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllSeatsStream(): Flow<List<Seat>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getSeatStream(id: Int): Flow<Seat?>

    /**
     * Insert item in the data source
     */
    suspend fun insertSeat(item: Seat)

    /**
     * Delete item from the data source
     */
    suspend fun deleteSeat(item: Seat)

    /**
     * Update item in the data source
     */
    suspend fun updateSeat(item: Seat)
}