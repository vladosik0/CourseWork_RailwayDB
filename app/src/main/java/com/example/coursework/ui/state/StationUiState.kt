package com.example.coursework.ui.state

import com.example.coursework.data.classes.Seat
import com.example.coursework.data.classes.Station

data class StationUiState(
    val id: Int = 0,
    val stationName: String = "",
    val actionEnabled: Boolean = false

)

fun StationUiState.toStation(): Station = Station(
    id = id,
    stationName = stationName
)

fun Station.toStationUiState(actionEnabled: Boolean = false): StationUiState = StationUiState(
    id = id,
    stationName = stationName,
    actionEnabled = actionEnabled
)

fun StationUiState.isValid() : Boolean{
    return stationName.isNotBlank()
}
