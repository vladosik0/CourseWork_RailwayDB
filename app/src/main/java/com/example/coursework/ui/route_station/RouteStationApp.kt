package com.example.coursework.ui.route_station


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.route_station.navigation.RouteStationNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun RouteStationApp(
    screenContent: MutableState<String>,
    navController: NavHostController = rememberNavController()
) {
    RouteStationNavHost(
        screenContent = screenContent,
        navController = navController
    )
}
