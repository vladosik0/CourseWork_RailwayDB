package com.example.coursework.ui.route_station.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.RouteStationsRepository
import com.example.coursework.ui.route_station.screens.RouteStationDetailsDestination
import com.example.coursework.ui.state.*
import kotlinx.coroutines.flow.*


class RouteStationDetailsViewModel(
        savedStateHandle: SavedStateHandle,
        private val routeStationsRepository: RouteStationsRepository
    ) : ViewModel() {

        private val routeStationId: Int = checkNotNull(savedStateHandle[RouteStationDetailsDestination.routeStationIdArg])
        val uiState: StateFlow<RouteStationUiState> = routeStationsRepository.getRouteStationStream(routeStationId)
            .filterNotNull()
            .map {
                it.toRouteStationUiState(actionEnabled = true)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RouteStationUiState()
            )
        suspend fun deleteItem() {
            routeStationsRepository.deleteRouteStation(uiState.value.toRouteStation())
        }

        companion object {
            private const val TIMEOUT_MILLIS = 5_000L
        }
    }