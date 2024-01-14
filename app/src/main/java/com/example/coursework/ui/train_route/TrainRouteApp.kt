package com.example.coursework.ui.train_route


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.train_route.navigation.TrainRouteNavHost


/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun TrainRouteApp(
    navController: NavHostController = rememberNavController()
) {
    TrainRouteNavHost(
        navController = navController
    )
}
