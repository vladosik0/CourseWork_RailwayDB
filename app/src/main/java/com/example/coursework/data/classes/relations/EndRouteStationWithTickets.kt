package com.example.coursework.data.classes.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coursework.data.classes.RouteStation
import com.example.coursework.data.classes.Ticket

data class EndRouteStationWithTickets(
    @Embedded val endStation: RouteStation,
    @Relation(
        parentColumn = "id",
        entityColumn = "endStationId"
    )
    val tickets: List<Ticket>
)
