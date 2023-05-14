package com.example.coursework.data.repositories.offlineRepositories

import com.example.coursework.data.classes.Seat
import com.example.coursework.data.daos.SeatDao
import com.example.coursework.data.repositories.SeatsRepository
import kotlinx.coroutines.flow.Flow

class OfflineSeatsRepository(private val seatDao:SeatDao):SeatsRepository {

    override fun getAllSeatsStream(): Flow<List<Seat>> = seatDao.getAllItems()

    override fun getSeatStream(id: Int): Flow<Seat?> = seatDao.getItem(id)

    override suspend fun insertSeat(item: Seat) = seatDao.insert(item)

    override suspend fun deleteSeat(item: Seat) = seatDao.delete(item)

    override suspend fun updateSeat(item: Seat) = seatDao.update(item)
}