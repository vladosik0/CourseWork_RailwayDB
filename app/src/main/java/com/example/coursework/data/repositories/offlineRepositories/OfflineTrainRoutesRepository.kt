package com.example.coursework.data.repositories.offlineRepositories

import com.example.coursework.data.classes.TrainRoute
import com.example.coursework.data.daos.TrainRouteDao
import com.example.coursework.data.repositories.TrainRoutesRepository
import kotlinx.coroutines.flow.Flow

class OfflineTrainRoutesRepository(private val trainRouteDao:TrainRouteDao):TrainRoutesRepository {

    override fun getAllTrainRoutesStream(): Flow<List<TrainRoute>> = trainRouteDao.getAllItems()

    override fun getTrainRouteStream(id: Int): Flow<TrainRoute?> = trainRouteDao.getItem(id)

    override suspend fun insertTrainRoute(item: TrainRoute) = trainRouteDao.insert(item)

    override suspend fun deleteTrainRoute(item: TrainRoute) = trainRouteDao.delete(item)

    override suspend fun updateTrainRoute(item: TrainRoute) = trainRouteDao.update(item)
}