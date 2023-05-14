package com.example.coursework.ui.station.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.StationsRepository
import com.example.coursework.ui.state.StationUiState
import com.example.coursework.ui.state.isValid
import com.example.coursework.ui.state.toStation
import com.example.coursework.ui.state.toStationUiState
import com.example.coursework.ui.station.screens.StationEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StationEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val stationsRepository: StationsRepository
) : ViewModel() {

    /**
     * Holds current station ui state
     */
    var stationUiState by mutableStateOf(StationUiState())
        private set

    private val stationId: Int = checkNotNull(savedStateHandle[StationEditDestination.stationIdArg])

    fun updateUiState(newStationUiState: StationUiState){
        stationUiState = newStationUiState.copy(actionEnabled = newStationUiState.isValid())
    }
    suspend fun updateStation(){
        if(stationUiState.isValid()){
            stationsRepository.updateStation(stationUiState.toStation())
        }
    }
    init{
        viewModelScope.launch {
            stationUiState = stationsRepository.getStationStream(stationId)
                .filterNotNull()
                .first()
                .toStationUiState(actionEnabled = true)
        }
    }

}