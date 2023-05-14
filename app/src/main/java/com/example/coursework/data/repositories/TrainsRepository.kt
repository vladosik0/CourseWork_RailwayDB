package com.example.coursework.data.repositories

import com.example.coursework.data.classes.Train
import kotlinx.coroutines.flow.Flow

interface TrainsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllTrainsStream(): Flow<List<Train>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getTrainStream(id: Int): Flow<Train?>

    /**
     * Insert item in the data source
     */
    suspend fun insertTrain(item: Train)

    /**
     * Delete item from the data source
     */
    suspend fun deleteTrain(item: Train)

    /**
     * Update item in the data source
     */
    suspend fun updateTrain(item: Train)
}