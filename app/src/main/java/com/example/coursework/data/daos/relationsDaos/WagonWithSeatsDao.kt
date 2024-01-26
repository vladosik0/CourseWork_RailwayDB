package com.example.coursework.data.daos.relationsDaos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.coursework.data.classes.relations.WagonWithSeats

@Dao
interface WagonWithSeatsDao {
    @Transaction
    @Query("SELECT * FROM wagon")
    fun getWagonsAndSeats(): List<WagonWithSeats>
}