package com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories

import com.example.coursework.data.classes.relations.SeatWithTickets
import com.example.coursework.data.daos.relationsDaos.SeatWithTicketsDao
import com.example.coursework.data.repositories.relationsRepositories.SeatWithTicketsRepository

class OfflineSeatWithTicketsRepository(
    private val seatWithTicketsDao: SeatWithTicketsDao
) : SeatWithTicketsRepository {
    override fun getSeatsAndTickets(): List<SeatWithTickets> =
        seatWithTicketsDao.getSeatsAndTrains()
}