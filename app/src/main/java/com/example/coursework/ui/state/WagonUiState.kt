package com.example.coursework.ui.state

import com.example.coursework.data.classes.Wagon

data class WagonUiState(
    val id: Int = 0,
    val wagonNumber: String = "",
    val wagonType: String = "",
    val trainId: String = "",
    val actionEnabled: Boolean = false
)

fun WagonUiState.toWagon(): Wagon = Wagon(
    id = id,
    wagonNumber = wagonNumber.toIntOrNull() ?: 0,
    wagonType = wagonType,
    trainId = trainId.toIntOrNull() ?: 0

)

fun Wagon.toWagonUiState(actionEnabled: Boolean = false): WagonUiState = WagonUiState(
    id = id,
    wagonNumber = wagonNumber.toString(),
    wagonType = wagonType,
    trainId = trainId.toString(),
    actionEnabled = actionEnabled
)

fun WagonUiState.isValid() : Boolean{
    return wagonNumber.isNotBlank() && wagonType.isNotBlank() && trainId.isNotBlank()
}