package com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories

import com.example.coursework.data.classes.relations.StationWithRouteStations
import com.example.coursework.data.daos.relationsDaos.StationWithRouteStationsDao
import com.example.coursework.data.repositories.relationsRepositories.StationWithRouteStationsRepository

class OfflineStationWithRouteStationsRepository(
    private val stationWithRouteStationsDao: StationWithRouteStationsDao
) : StationWithRouteStationsRepository {
    override fun getStationAndRouteStations(): List<StationWithRouteStations> =
        stationWithRouteStationsDao.getStationsAndRouteStations()
}