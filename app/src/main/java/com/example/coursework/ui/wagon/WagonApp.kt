package com.example.coursework.ui.wagon


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.wagon.navigation.WagonNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun WagonApp(
    navController: NavHostController = rememberNavController()
) {
    WagonNavHost(
        navController = navController
    )
}

