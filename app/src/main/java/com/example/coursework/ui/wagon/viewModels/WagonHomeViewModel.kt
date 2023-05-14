package com.example.coursework.ui.wagon.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.classes.Wagon
import com.example.coursework.data.repositories.WagonsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class WagonHomeViewModel(wagonsRepository: WagonsRepository) : ViewModel() {
    val wagonHomeUiState: StateFlow<WagonHomeUiState> =
        wagonsRepository.getAllWagonsStream().map { WagonHomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = WagonHomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

    /**
     * Ui State for HomeWagonScreen
     */
    data class WagonHomeUiState(val wagonList: List<Wagon> = listOf())