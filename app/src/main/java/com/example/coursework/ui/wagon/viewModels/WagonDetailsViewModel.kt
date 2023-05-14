package com.example.coursework.ui.wagon.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.WagonsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.wagon.screens.WagonDetailsDestination
import kotlinx.coroutines.flow.*


    class WagonDetailsViewModel(
        savedStateHandle: SavedStateHandle,
        private val wagonsRepository: WagonsRepository
    ) : ViewModel() {

        private val wagonId: Int = checkNotNull(savedStateHandle[WagonDetailsDestination.wagonIdArg])
        val uiState: StateFlow<WagonUiState> = wagonsRepository.getWagonStream(wagonId)
            .filterNotNull()
            .map {
                it.toWagonUiState(actionEnabled = true)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = WagonUiState()
            )
        suspend fun deleteWagon() {
            wagonsRepository.deleteWagon(uiState.value.toWagon())
        }

        companion object {
            private const val TIMEOUT_MILLIS = 5_000L
        }
    }