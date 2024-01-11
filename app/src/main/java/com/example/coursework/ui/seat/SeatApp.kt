package com.example.coursework.ui.seat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.seat.navigation.SeatNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun SeatApp(
    screenContent: MutableState<String>,
    navController: NavHostController = rememberNavController()
) {
    SeatNavHost(
        screenContent = screenContent,
        navController = navController
    )
}

