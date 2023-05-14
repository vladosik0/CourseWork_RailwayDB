package com.example.coursework.ui.train.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coursework.data.repositories.TrainsRepository
import com.example.coursework.ui.state.*

class TrainInputViewModel(private val trainsRepository: TrainsRepository) : ViewModel() {

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
        trainUiState = newTrainUiState.copy( actionEnabled = newTrainUiState.isValid())
    }

    suspend fun saveTrain(){
        if(trainUiState.isValid()){
            trainsRepository.insertTrain(trainUiState.toTrain())
        }
    }
}