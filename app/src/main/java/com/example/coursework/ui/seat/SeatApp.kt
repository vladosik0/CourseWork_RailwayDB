package com.example.coursework.ui.seat

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.seat.navigation.SeatNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun SeatApp(
    navController: NavHostController = rememberNavController()
) {
    SeatNavHost(
        navController = navController
    )
}

