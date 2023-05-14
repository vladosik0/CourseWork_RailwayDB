package com.example.coursework.data.repositories.offlineRepositories

import com.example.coursework.data.classes.Station
import com.example.coursework.data.daos.StationDao
import com.example.coursework.data.repositories.StationsRepository
import kotlinx.coroutines.flow.Flow

class OfflineStationsRepository(private val stationDao:StationDao):StationsRepository {

    override fun getAllStationsStream(): Flow<List<Station>> = stationDao.getAllItems()

    override fun getStationStream(id: Int): Flow<Station?> = stationDao.getItem(id)

    override suspend fun insertStation(item: Station) = stationDao.insert(item)

    override suspend fun deleteStation(item: Station) = stationDao.delete(item)

    override suspend fun updateStation(item: Station) = stationDao.update(item)
}