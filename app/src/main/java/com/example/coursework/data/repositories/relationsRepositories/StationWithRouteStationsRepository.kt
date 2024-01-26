package com.example.coursework.data.repositories.relationsRepositories

import com.example.coursework.data.classes.relations.StationWithRouteStations

interface StationWithRouteStationsRepository {
    fun getStationAndRouteStations(): List<StationWithRouteStations>
}