package com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories

import com.example.coursework.data.classes.relations.EndRouteStationWithTickets
import com.example.coursework.data.daos.relationsDaos.EndRouteStationWithTicketsDao
import com.example.coursework.data.repositories.relationsRepositories.EndRouteStationWithTicketsRepository

class OfflineEndRouteStationWithTicketsRepository(
    private val endRouteStationWithTicketsDao: EndRouteStationWithTicketsDao
) : EndRouteStationWithTicketsRepository {
    override fun getEndRouteStationsAndTickets(): List<EndRouteStationWithTickets> =
        endRouteStationWithTicketsDao.getEndRouteStationsAndTickets()
}