package com.example.coursework.ui.route_station.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.RouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.RouteWithRouteStationsRepository
import com.example.coursework.ui.route_station.screens.RouteStationEditDestination
import com.example.coursework.ui.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RouteStationEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val routeWithRouteStationsRepository: RouteWithRouteStationsRepository,
    private val routeStationsRepository: RouteStationsRepository
) : ViewModel() {

    /**
     * Holds current routeStation ui state
     */
    var routeStationUiState by mutableStateOf(RouteStationUiState())
        private set

    private val routeStationId: Int =
        checkNotNull(savedStateHandle[RouteStationEditDestination.routeStationIdArg])

    fun updateUiState(newRouteStationUiState: RouteStationUiState) {
        routeStationUiState =
            newRouteStationUiState.copy(actionEnabled = newRouteStationUiState.isValid())
    }

    suspend fun updateRouteStation(): String {
        /*if(routeStationUiState.isValid()){
            routeStationsRepository.updateRouteStation(routeStationUiState.toRouteStation())
        }*/
        val message = viewModelScope.async(Dispatchers.IO) {
            val routeWithRouteStationsList =
                routeWithRouteStationsRepository.getRouteStationsAndRouts()
            if (
                !routeWithRouteStationsList.any { it.route.id == routeStationUiState.routeId.toInt() }
            ) {
                "Route with this Id doesn't exist!"
            } else {
                routeStationsRepository.updateRouteStation(routeStationUiState.toRouteStation())
                "Row updated successfully."
            }
        }
        return message.await()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            routeStationUiState = routeStationsRepository.getRouteStationStream(routeStationId)
                .filterNotNull()
                .first()
                .toRouteStationUiState(actionEnabled = true)
        }
    }

}