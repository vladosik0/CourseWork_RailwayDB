package com.example.coursework.ui.train_route


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.train_route.navigation.TrainRouteNavHost


/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun TrainRouteApp(
    screenContent: MutableState<String>,
    navController: NavHostController = rememberNavController()
) {
    TrainRouteNavHost(
        screenContent = screenContent,
        navController = navController
    )
}
