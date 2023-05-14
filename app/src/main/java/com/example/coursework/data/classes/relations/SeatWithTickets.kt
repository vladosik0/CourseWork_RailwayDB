package com.example.coursework.data.classes.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coursework.data.classes.Seat
import com.example.coursework.data.classes.Ticket

data class SeatWithTickets(
    @Embedded val seat: Seat,
    @Relation(
        parentColumn = "id",
        entityColumn = "seatId"
    )
    val tickets: List<Ticket>
)

