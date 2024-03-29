package com.example.coursework.ui.ticket.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TicketsRepository
import com.example.coursework.data.repositories.relationsRepositories.EndRouteStationWithTicketsRepository
import com.example.coursework.data.repositories.relationsRepositories.SeatWithTicketsRepository
import com.example.coursework.data.repositories.relationsRepositories.StartRouteStationWithTicketsRepository
import com.example.coursework.ui.state.TicketUiState
import com.example.coursework.ui.state.isValid
import com.example.coursework.ui.state.toTicket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TicketInputViewModel(
    private val ticketsRepository: TicketsRepository,
    private val seatWithTicketsRepository: SeatWithTicketsRepository,
    private val startRouteStationWithTicketsRepository: StartRouteStationWithTicketsRepository,
    private val endRouteStationWithTicketsRepository: EndRouteStationWithTicketsRepository
) : ViewModel() {

    /**
     * Holds current ticket ui state
     */
    var ticketUiState by mutableStateOf(TicketUiState())
        private set

    /**
     * Updates the [ticketUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(newTicketUiState: TicketUiState) {
        ticketUiState = newTicketUiState.copy(actionEnabled = newTicketUiState.isValid())
    }

    suspend fun validateTicketInput(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val seatWithTicketsList = seatWithTicketsRepository.getSeatsAndTickets()
            val startRouteStationWithTicketsList =
                startRouteStationWithTicketsRepository.getStartRouteStationsAndTickets()
            val endRouteStationWithTicketsList =
                endRouteStationWithTicketsRepository.getEndRouteStationsAndTickets()
            if (
                !seatWithTicketsList.any { it.seat.id == ticketUiState.seatId.toInt() }
            ) {
                "Seat with this Id doesn't exist!"
            } else if (
                !startRouteStationWithTicketsList.any { it.startStation.id == ticketUiState.startStationId.toInt() }
            ) {
                "Start station with this Id doesn't exist!"
            } else if (
                !endRouteStationWithTicketsList.any { it.endStation.id == ticketUiState.endStationId.toInt() }
            ) {
                "End station with this Id doesn't exist!"
            } else {
                ticketsRepository.insertTicket(ticketUiState.toTicket())
                "Row added successfully."
            }
        }
        return message.await()
    }
}