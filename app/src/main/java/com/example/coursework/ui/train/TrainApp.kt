package com.example.coursework.ui.train


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.train.navigation.TrainNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun TrainApp(
    navController: NavHostController = rememberNavController()
) {
    TrainNavHost(
        navController = navController
    )
}