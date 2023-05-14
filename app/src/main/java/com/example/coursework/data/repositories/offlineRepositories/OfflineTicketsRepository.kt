package com.example.coursework.data.repositories.offlineRepositories

import com.example.coursework.data.classes.Ticket
import com.example.coursework.data.daos.TicketDao
import com.example.coursework.data.repositories.TicketsRepository
import kotlinx.coroutines.flow.Flow

class OfflineTicketsRepository(private val ticketDao: TicketDao):TicketsRepository {

    override fun getAllTicketsStream(): Flow<List<Ticket>> = ticketDao.getAllItems()

    override fun getTicketStream(id: Int): Flow<Ticket?> = ticketDao.getItem(id)

    override suspend fun insertTicket(item: Ticket) = ticketDao.insert(item)

    override suspend fun deleteTicket(item: Ticket) = ticketDao.delete(item)

    override suspend fun updateTicket(item: Ticket) = ticketDao.update(item)
}