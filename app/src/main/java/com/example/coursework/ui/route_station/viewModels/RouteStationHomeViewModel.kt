package com.example.coursework.ui.route_station.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.classes.RouteStation
import com.example.coursework.data.repositories.RouteStationsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class RouteStationHomeViewModel(routeStationsRepository: RouteStationsRepository) : ViewModel() {
    val routeStationHomeUiState: StateFlow<RouteStationHomeUiState> =
        routeStationsRepository.getAllRouteStationsStream().map { RouteStationHomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RouteStationHomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

    /**
     * Ui State for HomeStationScreen
     */
    data class RouteStationHomeUiState(val stationList: List<RouteStation> = listOf())