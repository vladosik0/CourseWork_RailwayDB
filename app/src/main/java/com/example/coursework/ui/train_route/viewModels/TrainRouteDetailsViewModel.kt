package com.example.coursework.ui.train_route.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TrainRoutesRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.train_route.screens.TrainRouteDetailsDestination
import kotlinx.coroutines.flow.*


class TrainRouteDetailsViewModel(
        savedStateHandle: SavedStateHandle,
        private val trainRoutesRepository: TrainRoutesRepository
    ) : ViewModel() {

        private val trainRouteId: Int = checkNotNull(savedStateHandle[TrainRouteDetailsDestination.trainRouteIdArg])
        val uiState: StateFlow<TrainRouteUiState> = trainRoutesRepository.getTrainRouteStream(trainRouteId)
            .filterNotNull()
            .map {
                it.toTrainRouteUiState(actionEnabled = true)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TrainRouteUiState()
            )
        suspend fun deleteTrainRoute() {
            trainRoutesRepository.deleteTrainRoute(uiState.value.toTrainRoute())
        }

        companion object {
            private const val TIMEOUT_MILLIS = 5_000L
        }
    }