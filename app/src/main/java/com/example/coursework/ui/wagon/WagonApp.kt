package com.example.coursework.ui.wagon


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.wagon.navigation.WagonNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun WagonApp(
    screenContent: MutableState<String>,
    navController: NavHostController = rememberNavController()
) {
    WagonNavHost(
        screenContent = screenContent,
        navController = navController
    )
}

