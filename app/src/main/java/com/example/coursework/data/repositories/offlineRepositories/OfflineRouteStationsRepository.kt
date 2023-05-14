package com.example.coursework.data.repositories.offlineRepositories

import com.example.coursework.data.classes.RouteStation
import com.example.coursework.data.daos.RouteStationDao
import com.example.coursework.data.repositories.RouteStationsRepository
import kotlinx.coroutines.flow.Flow

class OfflineRouteStationsRepository (private val routeStationDao: RouteStationDao) : RouteStationsRepository{

    override fun getAllRouteStationsStream(): Flow<List<RouteStation>> = routeStationDao.getAllItems()

    override fun getRouteStationStream(id: Int): Flow<RouteStation?> = routeStationDao.getItem(id)


    override suspend fun insertRouteStation(item: RouteStation) = routeStationDao.insert(item)


    override suspend fun deleteRouteStation(item: RouteStation) = routeStationDao.delete(item)


    override suspend fun updateRouteStation(item: RouteStation) = routeStationDao.update(item)
}