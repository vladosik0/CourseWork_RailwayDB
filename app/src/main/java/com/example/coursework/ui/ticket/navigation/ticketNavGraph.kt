package com.example.coursework.ui.ticket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coursework.ui.ticket.screens.*

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun TicketNavHost(
    screenContent: MutableState<String>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = TicketHomeDestination.route,
        modifier = modifier
    ) {
        composable(route = TicketHomeDestination.route) {
            TicketHomeScreen(
                screenContent = screenContent,
                navigateToTicketInput = { navController.navigate(TicketInputDestination.route) },
                navigateToTicketUpdate = {
                    navController.navigate("${TicketDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = TicketInputDestination.route) {
            TicketInputScreen(
                screenContent = screenContent,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = TicketDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(TicketDetailsDestination.ticketIdArg) {
                type = NavType.IntType
            })
        ) {
            TicketDetailsScreen(
                screenContent = screenContent,
                navigateToEditTicket = { navController.navigate("${TicketEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = TicketEditDestination.routeWithArgs,
            arguments = listOf(navArgument(TicketEditDestination.ticketIdArg) {
                type = NavType.IntType
            })
        ) {
            TicketEditScreen(
                screenContent = screenContent,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}