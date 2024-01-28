package com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories

import com.example.coursework.data.classes.relations.StartRouteStationWithTickets
import com.example.coursework.data.daos.relationsDaos.StartRouteStationWithTicketsDao
import com.example.coursework.data.repositories.relationsRepositories.StartRouteStationWithTicketsRepository

class OfflineStartRouteStationWithTicketsRepository(
    private val startRouteStationWithTicketsDao: StartRouteStationWithTicketsDao
) : StartRouteStationWithTicketsRepository {
    override fun getStartRouteStationsAndTickets(): List<StartRouteStationWithTickets> =
        startRouteStationWithTicketsDao.getStartRouteStationsAndTickets()
}