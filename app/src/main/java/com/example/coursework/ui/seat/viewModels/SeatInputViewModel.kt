package com.example.coursework.ui.seat.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coursework.data.repositories.SeatsRepository
import com.example.coursework.ui.state.*

class SeatInputViewModel(private val seatsRepository: SeatsRepository) : ViewModel() {

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
        seatUiState = newSeatUiState.copy( actionEnabled = newSeatUiState.isValid())
    }

    suspend fun saveSeat(){
        if(seatUiState.isValid()){
            seatsRepository.insertSeat(seatUiState.toSeat())
        }
    }
}