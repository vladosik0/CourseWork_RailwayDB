package com.example.coursework.ui.route_station.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coursework.data.repositories.RouteStationsRepository
import com.example.coursework.ui.state.RouteStationUiState
import com.example.coursework.ui.state.isValid
import com.example.coursework.ui.state.toRouteStation

class RouteStationInputViewModel(private val routeStationsRepository: RouteStationsRepository) : ViewModel() {

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
        routeStationUiState = newRouteStationUiState.copy( actionEnabled = newRouteStationUiState.isValid())
    }

    suspend fun saveRouteStation(){
        if(routeStationUiState.isValid()){
            routeStationsRepository.insertRouteStation(routeStationUiState.toRouteStation())
        }
    }
}