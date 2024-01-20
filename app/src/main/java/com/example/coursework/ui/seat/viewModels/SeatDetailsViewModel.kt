package com.example.coursework.ui.seat.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.SeatsRepository
import com.example.coursework.ui.seat.screens.SeatDetailsDestination
import com.example.coursework.ui.state.*
import kotlinx.coroutines.flow.*


class SeatDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val seatsRepository: SeatsRepository
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

    suspend fun deleteSeat() {
        seatsRepository.deleteSeat(uiState.value.toSeat())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}