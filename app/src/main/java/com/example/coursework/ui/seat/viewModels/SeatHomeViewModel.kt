package com.example.coursework.ui.seat.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.classes.Seat
import com.example.coursework.data.repositories.SeatsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class SeatHomeViewModel(seatsRepository: SeatsRepository) : ViewModel() {
    val seatHomeUiState: StateFlow<SeatHomeUiState> =
        seatsRepository.getAllSeatsStream().map { SeatHomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = SeatHomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

    /**
     * Ui State for SeatHomeScreen
     */
    data class SeatHomeUiState(val seatList: List<Seat> = listOf())