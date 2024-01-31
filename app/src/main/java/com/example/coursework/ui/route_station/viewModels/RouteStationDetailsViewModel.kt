package com.example.coursework.ui.route_station.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.RouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.EndRouteStationWithTicketsRepository
import com.example.coursework.data.repositories.relationsRepositories.StartRouteStationWithTicketsRepository
import com.example.coursework.ui.route_station.screens.RouteStationDetailsDestination
import com.example.coursework.ui.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*


class RouteStationDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val routeStationsRepository: RouteStationsRepository,
    private val startRouteStationWithTicketsRepository: StartRouteStationWithTicketsRepository,
    private val endRouteStationWithTicketsRepository: EndRouteStationWithTicketsRepository
) : ViewModel() {

    private val routeStationId: Int =
        checkNotNull(savedStateHandle[RouteStationDetailsDestination.routeStationIdArg])
    val uiState: StateFlow<RouteStationUiState> =
        routeStationsRepository.getRouteStationStream(routeStationId)
            .filterNotNull()
            .map {
                it.toRouteStationUiState(actionEnabled = true)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RouteStationUiState()
            )

    suspend fun validateRouteStationDeletion(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val startRouteStationWithTicketsList =
                startRouteStationWithTicketsRepository.getStartRouteStationsAndTickets()
            val endRouteStationWithTicketsList =
                endRouteStationWithTicketsRepository.getEndRouteStationsAndTickets()
            if (
                startRouteStationWithTicketsList.any { uiState.value.id == it.startStation.id && it.tickets.isNotEmpty() }
                || endRouteStationWithTicketsList.any { uiState.value.id == it.endStation.id && it.tickets.isNotEmpty() }
            ) {
                "This route station can't be deleted because it's used in existing ticket(-s)."
            } else {
                routeStationsRepository.deleteRouteStation(uiState.value.toRouteStation())
                "Row deleted successfully."
            }
        }
        return message.await()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}