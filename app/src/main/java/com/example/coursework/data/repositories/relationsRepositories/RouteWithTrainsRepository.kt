package com.example.coursework.data.repositories.relationsRepositories

import com.example.coursework.data.classes.relations.RouteWithTrains

interface RouteWithTrainsRepository {
    fun getRoutesAndTrains(): List<RouteWithTrains>
}