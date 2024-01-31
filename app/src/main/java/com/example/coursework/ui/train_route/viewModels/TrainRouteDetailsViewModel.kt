package com.example.coursework.ui.train_route.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TrainRoutesRepository
import com.example.coursework.data.repositories.relationsRepositories.RouteWithRouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.RouteWithTrainsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.train_route.screens.TrainRouteDetailsDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*


class TrainRouteDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val trainRoutesRepository: TrainRoutesRepository,
    private val routeWithTrainsRepository: RouteWithTrainsRepository,
    private val routeWithRouteStationsRepository: RouteWithRouteStationsRepository
) : ViewModel() {

    private val trainRouteId: Int =
        checkNotNull(savedStateHandle[TrainRouteDetailsDestination.trainRouteIdArg])
    val uiState: StateFlow<TrainRouteUiState> =
        trainRoutesRepository.getTrainRouteStream(trainRouteId)
            .filterNotNull()
            .map {
                it.toTrainRouteUiState(actionEnabled = true)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TrainRouteUiState()
            )

    suspend fun validateTrainRouteDeletion(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val routeWithTrainsList =
                routeWithTrainsRepository.getRoutesAndTrains()
            val routeWithRouteStationsList =
                routeWithRouteStationsRepository.getRouteStationsAndRouts()
            if (routeWithTrainsList.any { uiState.value.id == it.route.id && it.trains.isNotEmpty() }) {
                "This route can't be deleted because it's used in existing train(-s)."
            } else if (routeWithRouteStationsList.any { uiState.value.id == it.route.id && it.routeStations.isNotEmpty() }) {
                "This route can't be deleted because it's used in existing route station(-s)."
            } else {
                trainRoutesRepository.deleteTrainRoute(uiState.value.toTrainRoute())
                "Row deleted successfully."
            }
        }
        return message.await()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}