package com.example.coursework.data.classes.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coursework.data.classes.Seat
import com.example.coursework.data.classes.Wagon

data class WagonWithSeats(
    @Embedded val wagon: Wagon,
    @Relation(
        parentColumn = "id",
        entityColumn = "wagonId"
    )
    val seats: List<Seat>
)
