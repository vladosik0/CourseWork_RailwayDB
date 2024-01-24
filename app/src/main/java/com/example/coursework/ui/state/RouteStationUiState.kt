package com.example.coursework.ui.state

import com.example.coursework.data.classes.RouteStation

data class RouteStationUiState(
    val id: Int = 0,
    val routeSerialNumber: String = "",
    val stationId: String = "",
    val routeId: String = "",
    val actionEnabled: Boolean = false
)

fun RouteStationUiState.toRouteStation(): RouteStation = RouteStation(
    id = id,
    routeSerialNumber = routeSerialNumber.toIntOrNull() ?: 0,
    stationId = stationId.toIntOrNull() ?: 0,
    routeId = routeId.toIntOrNull() ?: 0
)

fun RouteStation.toRouteStationUiState(actionEnabled: Boolean = false): RouteStationUiState = RouteStationUiState(
    id = id,
    routeSerialNumber = routeSerialNumber.toString(),
    stationId = stationId.toString(),
    routeId = routeId.toString(),
    actionEnabled = actionEnabled
)

fun RouteStationUiState.isValid() : Boolean{
    return routeSerialNumber.isNotBlank() && stationId.isNotBlank() && routeId.isNotBlank()
}

