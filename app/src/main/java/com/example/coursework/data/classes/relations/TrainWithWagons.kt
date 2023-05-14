package com.example.coursework.data.classes.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coursework.data.classes.Train
import com.example.coursework.data.classes.Wagon

data class TrainWithWagons(
    @Embedded val train: Train,
    @Relation(
        parentColumn = "id",
        entityColumn = "trainId"
    )
    val wagons: List<Wagon>
)
