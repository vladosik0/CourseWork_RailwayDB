package com.example.coursework.ui.state

import com.example.coursework.data.classes.Ticket

data class TicketUiState(
    val id: Int = 0,
    val price: String = "",
    val startStationId: String = "",
    val endStationId: String = "",
    val seatId: String = "",
    val available: Boolean = true,
    val actionEnabled: Boolean = false
)

fun TicketUiState.toTicket(): Ticket = Ticket(
    id = id,
    price = price.toDoubleOrNull() ?: 0.0,
    startStationId = startStationId.toIntOrNull() ?: 0,
    endStationId = endStationId.toIntOrNull() ?: 0,
    seatId = seatId.toIntOrNull() ?: 0,
    available = available
)

fun Ticket.toTicketUiState(actionEnabled: Boolean = false): TicketUiState = TicketUiState(
    id = id,
    price = price.toString(),
    startStationId = startStationId.toString(),
    endStationId = endStationId.toString(),
    seatId = seatId.toString(),
    available = available,
    actionEnabled = actionEnabled
)

fun TicketUiState.isValid() : Boolean{
    return price.isNotBlank() && startStationId.isNotBlank() && endStationId.isNotBlank()
            && seatId.isNotBlank() && startStationId != endStationId
}

