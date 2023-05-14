package com.example.coursework.ui.state

import com.example.coursework.data.classes.TrainRoute

data class TrainRouteUiState(
    val id: Int = 0,
    val routeName: String = "",
    val actionEnabled:Boolean = false
)

fun TrainRouteUiState.toTrainRoute(): TrainRoute = TrainRoute(
    id = id,
    routeName = routeName
)

fun TrainRoute.toTrainRouteUiState(actionEnabled: Boolean = false): TrainRouteUiState = TrainRouteUiState(
    id = id,
    routeName = routeName,
    actionEnabled = actionEnabled
)

fun TrainRouteUiState.isValid() : Boolean{
    return routeName.isNotBlank()
}
