package com.example.coursework.ui.wagon.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coursework.data.repositories.WagonsRepository
import com.example.coursework.ui.state.*

class WagonInputViewModel(private val wagonsRepository: WagonsRepository) : ViewModel() {

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
        wagonUiState = newWagonUiState.copy( actionEnabled = newWagonUiState.isValid())
    }

    suspend fun saveWagon(){
        if(wagonUiState.isValid()){
            wagonsRepository.insertWagon(wagonUiState.toWagon())
        }
    }
}