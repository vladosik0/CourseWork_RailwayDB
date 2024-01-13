package com.example.coursework.ui.ticket


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coursework.ui.ticket.navigation.TicketNavHost


/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun TicketApp(
    screenContent: MutableState<String>,
    navController: NavHostController = rememberNavController()
) {
    TicketNavHost(
        screenContent = screenContent,
        navController = navController
    )
}

