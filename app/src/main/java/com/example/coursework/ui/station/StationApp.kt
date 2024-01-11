package com.example.coursework.ui.station


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.station.navigation.StationNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun StationApp(
    screenContent: MutableState<String>,
    navController: NavHostController = rememberNavController()
) {
    StationNavHost(
        screenContent = screenContent,
        navController = navController
    )
}
