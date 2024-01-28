package com.example.coursework.data.daos.relationsDaos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.coursework.data.classes.relations.SeatWithTickets

@Dao
interface SeatWithTicketsDao {
    @Transaction
    @Query("SELECT * FROM seat")
    fun getSeatsAndTrains(): List<SeatWithTickets>
}