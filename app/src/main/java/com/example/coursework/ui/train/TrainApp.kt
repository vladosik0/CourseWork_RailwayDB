package com.example.coursework.ui.train


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.train.navigation.TrainNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun TrainApp(
    screenContent: MutableState<String>,
    navController: NavHostController = rememberNavController()
) {
    TrainNavHost(
        screenContent = screenContent,
        navController = navController
    )
}