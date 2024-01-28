package com.example.coursework.data.repositories.relationsRepositories

import com.example.coursework.data.classes.relations.StartRouteStationWithTickets

interface StartRouteStationWithTicketsRepository {
    fun getStartRouteStationsAndTickets(): List<StartRouteStationWithTickets>
}