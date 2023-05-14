package com.example.coursework.ui.train_route.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.classes.TrainRoute
import com.example.coursework.data.repositories.TrainRoutesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class TrainRouteHomeViewModel(trainRoutesRepository: TrainRoutesRepository) : ViewModel() {
    val trainRouteHomeUiState: StateFlow<TrainRouteHomeUiState> =
        trainRoutesRepository.getAllTrainRoutesStream().map { TrainRouteHomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TrainRouteHomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

    /**
     * Ui State for HomeStationScreen
     */
    data class TrainRouteHomeUiState(val trainRouteList: List<TrainRoute> = listOf())