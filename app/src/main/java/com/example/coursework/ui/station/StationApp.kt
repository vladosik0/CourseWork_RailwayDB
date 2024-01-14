package com.example.coursework.ui.station


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.station.navigation.StationNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun StationApp(
    navController: NavHostController = rememberNavController()
) {
    StationNavHost(
        navController = navController
    )
}
