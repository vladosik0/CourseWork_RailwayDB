package com.example.coursework.ui.seat.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.SeatsRepository
import com.example.coursework.data.repositories.relationsRepositories.SeatWithTicketsRepository
import com.example.coursework.ui.seat.screens.SeatDetailsDestination
import com.example.coursework.ui.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*


class SeatDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val seatsRepository: SeatsRepository,
    private val seatWithTicketsRepository: SeatWithTicketsRepository
) : ViewModel() {

    private val seatId: Int = checkNotNull(savedStateHandle[SeatDetailsDestination.seatIdArg])
    val uiState: StateFlow<SeatUiState> = seatsRepository.getSeatStream(seatId)
        .filterNotNull()
        .map {
            it.toSeatUiState(actionEnabled = true)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = SeatUiState()
        )

    suspend fun validateSeatDeletion(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val seatWithTicketsList = seatWithTicketsRepository.getSeatsAndTickets()
            if (seatWithTicketsList.any { uiState.value.id == it.seat.id && it.tickets.isNotEmpty() }) {
                "This seat can't be deleted because it's used in existing ticket(-s)."
            } else {
                seatsRepository.deleteSeat(uiState.value.toSeat())
                "Row deleted successfully."
            }
        }
        return message.await()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}