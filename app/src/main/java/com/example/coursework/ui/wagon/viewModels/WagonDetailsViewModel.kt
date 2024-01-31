package com.example.coursework.ui.wagon.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.WagonsRepository
import com.example.coursework.data.repositories.relationsRepositories.WagonWithSeatsRepository
import com.example.coursework.ui.state.*
import com.example.coursework.ui.wagon.screens.WagonDetailsDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*


class WagonDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val wagonsRepository: WagonsRepository,
    private val wagonWithSeatsRepository: WagonWithSeatsRepository
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

    suspend fun validateWagonDeletion(): String {
        val message = viewModelScope.async(Dispatchers.IO) {
            val wagonWithSeatsList =
                wagonWithSeatsRepository.getWagonsAndSeats()
            if (wagonWithSeatsList.any { uiState.value.id == it.wagon.id && it.seats.isNotEmpty() }) {
                "This wagon can't be deleted because it's used in existing seat(-s)."
            } else {
                wagonsRepository.deleteWagon(uiState.value.toWagon())
                "Row deleted successfully."
            }
        }
        return message.await()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}