package com.example.coursework.ui.station.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.StationsRepository
import com.example.coursework.ui.state.StationUiState
import com.example.coursework.ui.state.toStation
import com.example.coursework.ui.state.toStationUiState
import com.example.coursework.ui.station.screens.StationDetailsDestination
import kotlinx.coroutines.flow.*


class StationDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val stationsRepository: StationsRepository
) : ViewModel() {

    private val stationId: Int = checkNotNull(savedStateHandle[StationDetailsDestination.stationIdArg])
    val uiState: StateFlow<StationUiState> = stationsRepository.getStationStream(stationId)
        .filterNotNull()
        .map {
            it.toStationUiState(actionEnabled = true)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = StationUiState()
        )

    suspend fun deleteItem() {
        stationsRepository.deleteStation(uiState.value.toStation())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}