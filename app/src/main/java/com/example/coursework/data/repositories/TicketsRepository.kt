package com.example.coursework.data.repositories

import com.example.coursework.data.classes.RouteStation
import com.example.coursework.data.classes.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllTicketsStream(): Flow<List<Ticket>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getTicketStream(id: Int): Flow<Ticket?>

    /**
     * Insert item in the data source
     */
    suspend fun insertTicket(item: Ticket)

    /**
     * Delete item from the data source
     */
    suspend fun deleteTicket(item: Ticket)

    /**
     * Update item in the data source
     */
    suspend fun updateTicket(item: Ticket)
}