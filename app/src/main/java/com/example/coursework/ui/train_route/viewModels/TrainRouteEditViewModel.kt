package com.example.coursework.ui.train_route.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TrainRoutesRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.train_route.screens.TrainRouteEditDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TrainRouteEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val trainRoutesRepository: TrainRoutesRepository
) : ViewModel() {

    /**
     * Holds current train_route ui state
     */
    var trainRouteUiState by mutableStateOf(TrainRouteUiState())
        private set

    private val trainRouteId: Int = checkNotNull(savedStateHandle[TrainRouteEditDestination.trainRouteIdArg])

    fun updateUiState(newTrainRouteUiState: TrainRouteUiState){
        trainRouteUiState = newTrainRouteUiState.copy(actionEnabled = newTrainRouteUiState.isValid())
    }
    suspend fun updateTrainRoute(){
        if(trainRouteUiState.isValid()){
            trainRoutesRepository.updateTrainRoute(trainRouteUiState.toTrainRoute())
        }
    }
    init{
        viewModelScope.launch(Dispatchers.IO) {
            trainRouteUiState = trainRoutesRepository.getTrainRouteStream(trainRouteId)
                .filterNotNull()
                .first()
                .toTrainRouteUiState(actionEnabled = true)
        }
    }

}