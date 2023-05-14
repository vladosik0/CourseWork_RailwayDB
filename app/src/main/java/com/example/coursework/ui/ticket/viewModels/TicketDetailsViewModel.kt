package com.example.coursework.ui.ticket.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TicketsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.ticket.screens.TicketDetailsDestination
import kotlinx.coroutines.flow.*


class TicketDetailsViewModel(
        savedStateHandle: SavedStateHandle,
        private val ticketsRepository: TicketsRepository
    ) : ViewModel() {

        private val ticketId: Int = checkNotNull(savedStateHandle[TicketDetailsDestination.ticketIdArg])
        val uiState: StateFlow<TicketUiState> = ticketsRepository.getTicketStream(ticketId)
            .filterNotNull()
            .map {
                it.toTicketUiState(actionEnabled = true)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TicketUiState()
            )
        suspend fun deleteTicket() {
            ticketsRepository.deleteTicket(uiState.value.toTicket())
        }

        companion object {
            private const val TIMEOUT_MILLIS = 5_000L
        }
    }