package com.example.coursework.ui.wagon.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.WagonsRepository
import com.example.coursework.data.repositories.relationsRepositories.TrainWithWagonsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.wagon.screens.WagonEditDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WagonEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val wagonsRepository: WagonsRepository,
    private val trainWithWagonsRepository: TrainWithWagonsRepository
) : ViewModel() {

    /**
     * Holds current wagon ui state
     */
    var wagonUiState by mutableStateOf(WagonUiState())
        private set

    private val wagonId: Int = checkNotNull(savedStateHandle[WagonEditDestination.wagonIdArg])

    fun updateUiState(newWagonUiState: WagonUiState) {
        wagonUiState = newWagonUiState.copy(actionEnabled = newWagonUiState.isValid())
    }

    suspend fun updateWagon(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val trainAndWagonsList = trainWithWagonsRepository.getTrainsAndWagons()
            if (
                !trainAndWagonsList.any { it.train.id == wagonUiState.trainId.toInt() }
            ) {
                "Train with this Id doesn't exist!"
            } else {
                wagonsRepository.updateWagon(wagonUiState.toWagon())
                "Row updated successfully."
            }
        }
        return message.await()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            wagonUiState = wagonsRepository.getWagonStream(wagonId)
                .filterNotNull()
                .first()
                .toWagonUiState(actionEnabled = true)
        }
    }

}