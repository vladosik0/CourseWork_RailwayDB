package com.example.coursework.ui.route_station


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.route_station.navigation.RouteStationNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun RouteStationApp(
    navController: NavHostController = rememberNavController()
) {
    RouteStationNavHost(
        navController = navController
    )
}
