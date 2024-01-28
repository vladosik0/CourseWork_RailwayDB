package com.example.coursework.ui.wagon.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.WagonsRepository
import com.example.coursework.data.repositories.relationsRepositories.TrainWithWagonsRepository
import com.example.coursework.ui.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class WagonInputViewModel(
    private val wagonsRepository: WagonsRepository,
    private val trainWithWagonsRepository: TrainWithWagonsRepository
) : ViewModel() {

    /**
     * Holds current wagon ui state
     */
    var wagonUiState by mutableStateOf(WagonUiState())
        private set

    /**
     * Updates the [wagonUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(newWagonUiState: WagonUiState) {
        wagonUiState = newWagonUiState.copy(actionEnabled = newWagonUiState.isValid())
    }

    suspend fun validateWagonInput(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val trainAndWagonsList = trainWithWagonsRepository.getTrainsAndWagons()
            if (
                !trainAndWagonsList.any { it.train.id == wagonUiState.trainId.toInt() }
            ) {
                "Train with this Id doesn't exist!"
            } else {
                wagonsRepository.insertWagon(wagonUiState.toWagon())
                "Row added successfully."
            }
        }
        return message.await()
    }
}