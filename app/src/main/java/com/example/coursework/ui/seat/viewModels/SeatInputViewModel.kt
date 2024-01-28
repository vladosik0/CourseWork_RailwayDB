package com.example.coursework.ui.seat.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.SeatsRepository
import com.example.coursework.data.repositories.relationsRepositories.WagonWithSeatsRepository
import com.example.coursework.ui.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SeatInputViewModel(
    private val seatsRepository: SeatsRepository,
    private val wagonWithSeatsRepository: WagonWithSeatsRepository
) : ViewModel() {

    /**
     * Holds current seat ui state
     */
    var seatUiState by mutableStateOf(SeatUiState())
        private set

    /**
     * Updates the [seatUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(newSeatUiState: SeatUiState) {
        seatUiState = newSeatUiState.copy(actionEnabled = newSeatUiState.isValid())
    }

    suspend fun validateSeatInput(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val wagonWithSeatsList = wagonWithSeatsRepository.getWagonsAndSeats()
            if (
                !wagonWithSeatsList.any { it.wagon.id == seatUiState.wagonId.toInt() }
            ) {
                "Wagon with this Id doesn't exist!"
            } else {
                seatsRepository.insertSeat(seatUiState.toSeat())
                "Row added successfully."
            }
        }
        return message.await()
    }
}