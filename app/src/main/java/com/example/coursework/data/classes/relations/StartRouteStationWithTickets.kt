package com.example.coursework.data.classes.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coursework.data.classes.RouteStation
import com.example.coursework.data.classes.Ticket

data class StartRouteStationWithTickets(
    @Embedded val startStation: RouteStation,
    @Relation(
        parentColumn = "id",
        entityColumn = "startStationId"
    )
    val tickets: List<Ticket>
)
