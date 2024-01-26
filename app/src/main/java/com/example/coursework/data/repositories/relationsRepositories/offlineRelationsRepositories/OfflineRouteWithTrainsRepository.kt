package com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories

import com.example.coursework.data.classes.relations.RouteWithTrains
import com.example.coursework.data.daos.relationsDaos.RouteWithTrainsDao
import com.example.coursework.data.repositories.relationsRepositories.RouteWithTrainsRepository

class OfflineRouteWithTrainsRepository(
    private val routeWithTrainsDao: RouteWithTrainsDao
) : RouteWithTrainsRepository {
    override fun getRoutesAndTrains(): List<RouteWithTrains> =
        routeWithTrainsDao.getRoutesAndTrains()
}