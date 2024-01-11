package com.example.coursework

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.coursework.ui.route_station.RouteStationApp
import com.example.coursework.ui.seat.SeatApp
import com.example.coursework.ui.station.StationApp
import com.example.coursework.ui.ticket.TicketApp
import com.example.coursework.ui.train.TrainApp
import com.example.coursework.ui.train_route.TrainRouteApp
import com.example.coursework.ui.wagon.WagonApp


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CourseWorkApp() {
    val screenContent = remember { mutableStateOf("train") }
    when (screenContent.value) {
        "train" -> TrainApp(screenContent)
        "train_route" -> TrainRouteApp(screenContent)
        "seat" -> SeatApp(screenContent)
        "wagon" -> WagonApp(screenContent)
        "ticket" -> TicketApp(screenContent)
        "route_station" -> RouteStationApp()
        "station" -> StationApp(screenContent)
        "About project" -> AboutApp()
    }
}