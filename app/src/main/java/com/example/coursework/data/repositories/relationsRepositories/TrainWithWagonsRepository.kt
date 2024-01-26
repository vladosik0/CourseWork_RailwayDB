package com.example.coursework.data.repositories.relationsRepositories

import com.example.coursework.data.classes.relations.TrainWithWagons

interface TrainWithWagonsRepository {
    fun getTrainsAndWagons(): List<TrainWithWagons>
}