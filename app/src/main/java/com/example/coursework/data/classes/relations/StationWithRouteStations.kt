package com.example.coursework.data.classes.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coursework.data.classes.RouteStation
import com.example.coursework.data.classes.Station

data class StationWithRouteStations(
    @Embedded val station:Station,
    @Relation(
        parentColumn = "id",
        entityColumn = "stationId"
    )
    val routeStations: List<RouteStation>
)
