package com.example.coursework.ui.wagon.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.WagonsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.wagon.screens.WagonEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WagonEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val wagonsRepository: WagonsRepository
) : ViewModel() {

    /**
     * Holds current wagon ui state
     */
    var wagonUiState by mutableStateOf(WagonUiState())
        private set

    private val wagonId: Int = checkNotNull(savedStateHandle[WagonEditDestination.wagonIdArg])

    fun updateUiState(newWagonUiState: WagonUiState){
        wagonUiState = newWagonUiState.copy(actionEnabled = newWagonUiState.isValid())
    }
    suspend fun updateWagon(){
        if(wagonUiState.isValid()){
            wagonsRepository.updateWagon(wagonUiState.toWagon())
        }
    }
    init{
        viewModelScope.launch {
            wagonUiState = wagonsRepository.getWagonStream(wagonId)
                .filterNotNull()
                .first()
                .toWagonUiState(actionEnabled = true)
        }
    }

}