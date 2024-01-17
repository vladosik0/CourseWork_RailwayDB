package com.example.coursework

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.route_station.RouteStationApp
import com.example.coursework.ui.seat.SeatApp
import com.example.coursework.ui.station.StationApp
import com.example.coursework.ui.theme.CourseWorkTheme
import com.example.coursework.ui.ticket.TicketApp
import com.example.coursework.ui.train.TrainApp
import com.example.coursework.ui.train_route.TrainRouteApp
import com.example.coursework.ui.wagon.WagonApp


@Composable
fun CourseWorkApp(
    viewModel: CourseWorkViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val screenContentUiState by viewModel.databaseTableNameUiState.collectAsState()
    when (screenContentUiState.tableName) {
        "train" -> TrainApp()
        "train_route" -> TrainRouteApp()
        "seat" -> SeatApp()
        "wagon" -> WagonApp()
        "ticket" -> TicketApp()
        "route_station" -> RouteStationApp()
        "station" -> StationApp()
        "About project" -> AboutApp()
    }
}