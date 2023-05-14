package com.example.coursework.data.repositories.offlineRepositories

import com.example.coursework.data.classes.Wagon
import com.example.coursework.data.daos.WagonDao
import com.example.coursework.data.repositories.WagonsRepository
import kotlinx.coroutines.flow.Flow

class OfflineWagonsRepository(private val wagonDao:WagonDao):WagonsRepository {

    override fun getAllWagonsStream(): Flow<List<Wagon>> = wagonDao.getAllItems()

    override fun getWagonStream(id: Int): Flow<Wagon?> = wagonDao.getItem(id)

    override suspend fun insertWagon(item: Wagon) = wagonDao.insert(item)

    override suspend fun deleteWagon(item: Wagon) = wagonDao.delete(item)

    override suspend fun updateWagon(item: Wagon) = wagonDao.update(item)
}