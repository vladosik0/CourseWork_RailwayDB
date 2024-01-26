package com.example.coursework.ui.train.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TrainsRepository
import com.example.coursework.data.repositories.relationsRepositories.RouteWithTrainsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.train.screens.TrainEditDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TrainEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val trainsRepository: TrainsRepository,
    private val routeWithTrainsRepository: RouteWithTrainsRepository
) : ViewModel() {

    /**
     * Holds current train ui state
     */
    var trainUiState by mutableStateOf(TrainUiState())
        private set

    private val trainId: Int = checkNotNull(savedStateHandle[TrainEditDestination.trainIdArg])

    fun updateUiState(newTrainUiState: TrainUiState) {
        trainUiState = newTrainUiState.copy(actionEnabled = newTrainUiState.isValid())
    }

    suspend fun updateTrain(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val routeWithTrainsList = routeWithTrainsRepository.getRoutesAndTrains()
            if (
                !routeWithTrainsList.any { it.route.id == trainUiState.routeId.toInt() }
            ) {
                "Route with this Id doesn't exist!"
            } /*else if (
                !stationWithRouteStationsList.any { it.station.id == routeStationUiState.stationId.toInt() }
            ) {
                "Station with this Id doesn't exist!"
            } */else {
                trainsRepository.updateTrain(trainUiState.toTrain())
                "Row updated successfully."
            }
        }
        return message.await()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            trainUiState = trainsRepository.getTrainStream(trainId)
                .filterNotNull()
                .first()
                .toTrainUiState(actionEnabled = true)
        }
    }

}