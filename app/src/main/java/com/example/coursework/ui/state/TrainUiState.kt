package com.example.coursework.ui.state

import com.example.coursework.data.classes.Train

data class TrainUiState(
    val id: Int = 0,
    val trainNumber: String = "",
    val routeId: String = "",
    val departureDate: String = "",
    val arrivalDate: String = "",
    val actionEnabled: Boolean = false
)

fun TrainUiState.toTrain(): Train = Train(
    id = id,
    trainNumber = trainNumber.toIntOrNull() ?: 0,
    routeId = routeId.toIntOrNull() ?: 0,
    departureDate = departureDate,
    arrivalDate = arrivalDate
)

fun Train.toTrainUiState(actionEnabled: Boolean = false): TrainUiState = TrainUiState(
    id = id,
    trainNumber = trainNumber.toString(),
    routeId = routeId.toString(),
    departureDate = departureDate,
    arrivalDate = arrivalDate,
    actionEnabled = actionEnabled
)

fun TrainUiState.isValid(): Boolean {
    return trainNumber.isNotBlank() && routeId.isNotBlank()
            && departureDate != arrivalDate
}