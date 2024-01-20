package com.example.coursework.ui.ticket.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TicketsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.ticket.screens.TicketEditDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TicketEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val ticketsRepository: TicketsRepository
) : ViewModel() {

    /**
     * Holds current ticket ui state
     */
    var ticketUiState by mutableStateOf(TicketUiState())
        private set

    private val ticketId: Int = checkNotNull(savedStateHandle[TicketEditDestination.ticketIdArg])

    fun updateUiState(newTicketUiState: TicketUiState){
        ticketUiState = newTicketUiState.copy(actionEnabled = newTicketUiState.isValid())
    }
    suspend fun updateTicket(){
        if(ticketUiState.isValid()){
            ticketsRepository.updateTicket(ticketUiState.toTicket())
        }
    }
    init{
        viewModelScope.launch(Dispatchers.IO) {
            ticketUiState = ticketsRepository.getTicketStream(ticketId)
                .filterNotNull()
                .first()
                .toTicketUiState(actionEnabled = true)
        }
    }

}