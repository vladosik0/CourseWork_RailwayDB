package com.example.coursework.data.repositories.relationsRepositories

import com.example.coursework.data.classes.relations.RouteWithRouteStations

interface RouteWithRouteStationsRepository {
    fun getRouteStationsAndRouts(): List<RouteWithRouteStations>
}