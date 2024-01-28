package com.example.coursework.ui.train.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TrainsRepository
import com.example.coursework.data.repositories.relationsRepositories.RouteWithTrainsRepository
import com.example.coursework.ui.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TrainInputViewModel(
    private val trainsRepository: TrainsRepository,
    private val routeWithTrainsRepository: RouteWithTrainsRepository
) : ViewModel() {

    /**
     * Holds current train ui state
     */
    var trainUiState by mutableStateOf(TrainUiState())
        private set

    /**
     * Updates the [trainUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(newTrainUiState: TrainUiState) {
        trainUiState = newTrainUiState.copy(actionEnabled = newTrainUiState.isValid())
    }

    suspend fun validateTrainInput(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val routeWithTrainsList = routeWithTrainsRepository.getRoutesAndTrains()
            if (
                !routeWithTrainsList.any { it.route.id == trainUiState.routeId.toInt() }
            ) {
                "Route with this Id doesn't exist!"
            } else {
                trainsRepository.insertTrain(trainUiState.toTrain())
                "Row added successfully."
            }
        }
        return message.await()
    }
}