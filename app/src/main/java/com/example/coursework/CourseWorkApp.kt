package com.example.coursework

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coursework.ui.TopAppBarDropdownMenu
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
    val screenContent = remember { mutableStateOf("Choose database table") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = screenContent.value)
                },
                actions = {
                    TopAppBarDropdownMenu(screenContent)
                })
        }
    ) {
        when (screenContent.value) {
            "Choose database table" -> Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)) {
                Text(screenContent.value)
            }
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
}