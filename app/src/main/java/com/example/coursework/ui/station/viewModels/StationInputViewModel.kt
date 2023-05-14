package com.example.coursework.ui.station.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coursework.data.repositories.StationsRepository
import com.example.coursework.ui.state.StationUiState
import com.example.coursework.ui.state.isValid
import com.example.coursework.ui.state.toStation

class StationInputViewModel(private val stationsRepository: StationsRepository) : ViewModel() {

    /**
     * Holds current station ui state
     */
    var stationUiState by mutableStateOf(StationUiState())
        private set

    /**
     * Updates the [stationUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(newStationUiState: StationUiState) {
        stationUiState = newStationUiState.copy( actionEnabled = newStationUiState.isValid())
    }

    suspend fun saveStation(){
        if(stationUiState.isValid()){
            stationsRepository.insertStation(stationUiState.toStation())
        }
    }
}