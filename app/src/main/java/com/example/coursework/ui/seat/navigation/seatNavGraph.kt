package com.example.coursework.ui.seat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coursework.ui.seat.screens.*

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun SeatNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = SeatHomeDestination.route,
        modifier = modifier
    ) {
        composable(route = SeatHomeDestination.route) {
            SeatHomeScreen(
                navigateToSeatInput = { navController.navigate(SeatInputDestination.route) },
                navigateToSeatUpdate = {
                    navController.navigate("${SeatDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = SeatInputDestination.route) {
            SeatInputScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = SeatDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(SeatDetailsDestination.seatIdArg) {
                type = NavType.IntType
            })
        ) {
            SeatDetailsScreen(
                navigateToEditSeat = { navController.navigate("${SeatEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = SeatEditDestination.routeWithArgs,
            arguments = listOf(navArgument(SeatEditDestination.seatIdArg) {
                type = NavType.IntType
            })
        ) {
            SeatEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}