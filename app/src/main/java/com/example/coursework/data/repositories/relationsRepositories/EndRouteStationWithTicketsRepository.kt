package com.example.coursework.data.repositories.relationsRepositories

import com.example.coursework.data.classes.relations.EndRouteStationWithTickets

interface EndRouteStationWithTicketsRepository {
    fun getEndRouteStationsAndTickets(): List<EndRouteStationWithTickets>
}