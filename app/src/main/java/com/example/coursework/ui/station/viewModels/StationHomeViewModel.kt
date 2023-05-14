package com.example.coursework.ui.station.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.classes.Station
import com.example.coursework.data.repositories.StationsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class StationHomeViewModel(stationsRepository: StationsRepository) : ViewModel() {
    val stationHomeUiState: StateFlow<StationHomeUiState> =
        stationsRepository.getAllStationsStream().map { StationHomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = StationHomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

    /**
     * Ui State for HomeStationScreen
     */
    data class StationHomeUiState(val stationList: List<Station> = listOf())