package com.example.coursework.data.daos.relationsDaos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.coursework.data.classes.relations.TrainWithWagons

@Dao
interface TrainWithWagonsDao {
    @Transaction
    @Query("SELECT * FROM train")
    fun getTrainsAndWagons(): List<TrainWithWagons>
}