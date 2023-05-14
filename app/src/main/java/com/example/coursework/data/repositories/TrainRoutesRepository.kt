package com.example.coursework.data.repositories

import com.example.coursework.data.classes.TrainRoute
import kotlinx.coroutines.flow.Flow

interface TrainRoutesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllTrainRoutesStream(): Flow<List<TrainRoute>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getTrainRouteStream(id: Int): Flow<TrainRoute?>

    /**
     * Insert item in the data source
     */
    suspend fun insertTrainRoute(item: TrainRoute)

    /**
     * Delete item from the data source
     */
    suspend fun deleteTrainRoute(item: TrainRoute)

    /**
     * Update item in the data source
     */
    suspend fun updateTrainRoute(item: TrainRoute)
}