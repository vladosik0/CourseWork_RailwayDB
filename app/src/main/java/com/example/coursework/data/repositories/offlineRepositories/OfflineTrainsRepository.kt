package com.example.coursework.data.repositories.offlineRepositories

import com.example.coursework.data.classes.Train
import com.example.coursework.data.daos.TrainDao
import com.example.coursework.data.repositories.TrainsRepository
import kotlinx.coroutines.flow.Flow

class OfflineTrainsRepository(private val trainDao:TrainDao):TrainsRepository {

    override fun getAllTrainsStream(): Flow<List<Train>> = trainDao.getAllItems()

    override fun getTrainStream(id: Int): Flow<Train?> = trainDao.getItem(id)

    override suspend fun insertTrain(item: Train) = trainDao.insert(item)

    override suspend fun deleteTrain(item: Train) = trainDao.delete(item)

    override suspend fun updateTrain(item: Train) = trainDao.update(item)
}