package com.example.coursework.ui.train_route.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coursework.data.repositories.TrainRoutesRepository
import com.example.coursework.ui.state.*

class TrainRouteInputViewModel(private val trainRoutesRepository: TrainRoutesRepository) : ViewModel() {

    /**
     * Holds current station ui state
     */
    var trainRouteUiState by mutableStateOf(TrainRouteUiState())
        private set

    /**
     * Updates the [trainRouteUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(newTrainRouteUiState: TrainRouteUiState) {
        trainRouteUiState = newTrainRouteUiState.copy( actionEnabled = newTrainRouteUiState.isValid())
    }

    suspend fun saveTrainRoute(){
        if(trainRouteUiState.isValid()){
            trainRoutesRepository.insertTrainRoute(trainRouteUiState.toTrainRoute())
        }
    }
}