package com.example.coursework.ui.route_station.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.RouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.RouteWithRouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.StationWithRouteStationsRepository
import com.example.coursework.ui.state.RouteStationUiState
import com.example.coursework.ui.state.isValid
import com.example.coursework.ui.state.toRouteStation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RouteStationInputViewModel(
    private val routeWithRouteStationsRepository: RouteWithRouteStationsRepository,
    private val routeStationsRepository: RouteStationsRepository,
    private val stationWithRouteStationsRepository: StationWithRouteStationsRepository
) : ViewModel() {

    /**
     * Holds current routeStation ui state
     */
    var routeStationUiState by mutableStateOf(RouteStationUiState())
        private set

    /**
     * Updates the [routeStationUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(newRouteStationUiState: RouteStationUiState) {
        routeStationUiState =
            newRouteStationUiState.copy(actionEnabled = newRouteStationUiState.isValid())
    }

    suspend fun validateRouteStationInput(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val routeWithRouteStationsList =
                routeWithRouteStationsRepository.getRouteStationsAndRouts()
            val stationWithRouteStationsList =
                stationWithRouteStationsRepository.getStationAndRouteStations()
            if (
                !routeWithRouteStationsList.any { it.route.id == routeStationUiState.routeId.toInt() }
            ) {
                "Route with this Id doesn't exist!"
            } else if (
                !stationWithRouteStationsList.any { it.station.id == routeStationUiState.stationId.toInt() }
            ) {
                "Station with this Id doesn't exist!"
            } else {
                saveRouteStation()
                "Row added successfully."
            }
        }
        return message.await()
    }

    private suspend fun saveRouteStation() {
        routeStationsRepository.insertRouteStation(routeStationUiState.toRouteStation())
    }
}