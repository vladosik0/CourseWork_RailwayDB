package com.example.coursework.ui.seat.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.SeatsRepository
import com.example.coursework.ui.seat.screens.SeatEditDestination
import com.example.coursework.ui.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SeatEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val seatsRepository: SeatsRepository
) : ViewModel() {

    /**
     * Holds current seat ui state
     */
    var seatUiState by mutableStateOf(SeatUiState())
        private set

    private val seatId: Int = checkNotNull(savedStateHandle[SeatEditDestination.seatIdArg])

    fun updateUiState(newSeatUiState: SeatUiState){
        seatUiState = newSeatUiState.copy(actionEnabled = newSeatUiState.isValid())
    }
    suspend fun updateSeat(){
        if(seatUiState.isValid()){
            seatsRepository.updateSeat(seatUiState.toSeat())
        }
    }
    init{
        viewModelScope.launch(Dispatchers.IO) {
            seatUiState = seatsRepository.getSeatStream(seatId)
                .filterNotNull()
                .first()
                .toSeatUiState(actionEnabled = true)
        }
    }

}