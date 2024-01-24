package com.example.coursework.data.classes.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coursework.data.classes.RouteStation
import com.example.coursework.data.classes.TrainRoute

data class RouteWithRouteStations(
    @Embedded val route: TrainRoute,
    @Relation(
        parentColumn = "id",
        entityColumn = "routeId"
    )
    val routeStations: List<RouteStation>
)