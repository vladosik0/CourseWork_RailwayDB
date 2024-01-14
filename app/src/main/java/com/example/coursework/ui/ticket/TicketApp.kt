package com.example.coursework.ui.ticket


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.ticket.navigation.TicketNavHost


/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun TicketApp(
    navController: NavHostController = rememberNavController()
) {
    TicketNavHost(
        navController = navController
    )
}

