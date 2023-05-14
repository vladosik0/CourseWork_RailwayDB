package com.example.coursework.ui.state

import com.example.coursework.data.classes.Seat

data class SeatUiState(
    val id: Int = 0,
    val seatNumber: String = "",
    val wagonId: String = "",
    val actionEnabled: Boolean = false
)

fun SeatUiState.toSeat(): Seat = Seat(
    id = id,
    seatNumber = seatNumber.toIntOrNull() ?: 0,
    wagonId = wagonId.toIntOrNull() ?: 0
)

fun Seat.toSeatUiState(actionEnabled: Boolean = false): SeatUiState = SeatUiState(
    id = id,
    seatNumber = seatNumber.toString(),
    wagonId = wagonId.toString(),
    actionEnabled = actionEnabled
)

fun SeatUiState.isValid() : Boolean{
    return seatNumber.isNotBlank() && wagonId.isNotBlank()
}
