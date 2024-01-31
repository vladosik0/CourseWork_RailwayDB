package com.example.coursework.ui.train.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.TrainsRepository
import com.example.coursework.data.repositories.relationsRepositories.TrainWithWagonsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.train.screens.TrainDetailsDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*


class TrainDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val trainsRepository: TrainsRepository,
    private val trainWithWagonsRepository: TrainWithWagonsRepository
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

    suspend fun validateTrainDeletion(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val trainWithWagonsList =
                trainWithWagonsRepository.getTrainsAndWagons()
            if (trainWithWagonsList.any { uiState.value.id == it.train.id && it.wagons.isNotEmpty() }) {
                "This train can't be deleted because it's used in existing wagon(-s)."
            } else {
                trainsRepository.deleteTrain(uiState.value.toTrain())
                "Row deleted successfully."
            }
        }
        return message.await()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}