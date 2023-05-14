package com.example.coursework.ui.ticket.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coursework.data.repositories.TicketsRepository
import com.example.coursework.ui.state.TicketUiState
import com.example.coursework.ui.state.isValid
import com.example.coursework.ui.state.toTicket

class TicketInputViewModel(private val ticketsRepository: TicketsRepository) : ViewModel() {

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
        ticketUiState = newTicketUiState.copy( actionEnabled = newTicketUiState.isValid())
    }

    suspend fun saveTicket(){
        if(ticketUiState.isValid()){
            ticketsRepository.insertTicket(ticketUiState.toTicket())
        }
    }
}