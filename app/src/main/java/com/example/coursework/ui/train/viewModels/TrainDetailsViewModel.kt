package com.example.coursework.ui.train.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TrainsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.train.screens.TrainDetailsDestination
import kotlinx.coroutines.flow.*


class TrainDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val trainsRepository: TrainsRepository
) : ViewModel() {

    private val trainId: Int = checkNotNull(savedStateHandle[TrainDetailsDestination.trainIdArg])
    val uiState: StateFlow<TrainUiState> = trainsRepository.getTrainStream(trainId)
        .filterNotNull()
        .map {
            it.toTrainUiState(actionEnabled = true)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = TrainUiState()
        )

    suspend fun deleteTrain() {
        trainsRepository.deleteTrain(uiState.value.toTrain())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}