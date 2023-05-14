package com.example.coursework.data.classes.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coursework.data.classes.Train
import com.example.coursework.data.classes.TrainRoute

data class RouteWithTrains(
    @Embedded val route: TrainRoute,
    @Relation(
        parentColumn = "id",
        entityColumn = "routeId"
    )
    val trains: List<Train>
)
