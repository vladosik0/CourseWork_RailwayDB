package com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories

import com.example.coursework.data.daos.relationsDaos.RouteWithRouteStationsDao
import com.example.coursework.data.repositories.relationsRepositories.RouteWithRouteStationsRepository

class OfflineRouteWithRouteStationsRepository(
    private val routeWithRouteStationsDao: RouteWithRouteStationsDao
) : RouteWithRouteStationsRepository {

    override fun getRouteStationsAndRouts() =
        routeWithRouteStationsDao.getRouteStationsAndRouts()
}