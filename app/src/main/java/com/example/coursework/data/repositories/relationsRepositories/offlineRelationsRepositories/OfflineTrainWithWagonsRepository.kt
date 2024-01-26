package com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories

import com.example.coursework.data.classes.relations.TrainWithWagons
import com.example.coursework.data.daos.relationsDaos.TrainWithWagonsDao
import com.example.coursework.data.repositories.relationsRepositories.TrainWithWagonsRepository

class OfflineTrainWithWagonsRepository(
    private val trainWithWagonsDao: TrainWithWagonsDao
) : TrainWithWagonsRepository {
    override fun getTrainsAndWagons(): List<TrainWithWagons> = trainWithWagonsDao.getTrainsAndWagons()
}