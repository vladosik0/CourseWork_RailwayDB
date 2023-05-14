package com.example.coursework.ui.train.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.classes.Train
import com.example.coursework.data.repositories.TrainsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class TrainHomeViewModel(trainsRepository: TrainsRepository) : ViewModel() {
    val trainHomeUiState: StateFlow<TrainHomeUiState> =
        trainsRepository.getAllTrainsStream().map { TrainHomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TrainHomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

    /**
     * Ui State for TrainHomeScreen
     */
    data class TrainHomeUiState(val trainList: List<Train> = listOf())