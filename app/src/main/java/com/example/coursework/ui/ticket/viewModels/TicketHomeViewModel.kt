package com.example.coursework.ui.ticket.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.classes.Station
import com.example.coursework.data.classes.Ticket
import com.example.coursework.data.repositories.StationsRepository
import com.example.coursework.data.repositories.TicketsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class TicketHomeViewModel(ticketsRepository: TicketsRepository) : ViewModel() {
    val ticketHomeUiState: StateFlow<TicketHomeUiState> =
        ticketsRepository.getAllTicketsStream().map { TicketHomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TicketHomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

    /**
     * Ui State for TicketHomeScreen
     */
    data class TicketHomeUiState(val ticketList: List<Ticket> = listOf())