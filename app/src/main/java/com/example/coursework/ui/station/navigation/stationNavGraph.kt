package com.example.coursework.ui.station.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coursework.ui.station.screens.*

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun StationNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = StationHomeDestination.route,
        modifier = modifier
    ) {
        composable(route = StationHomeDestination.route) {
            StationHomeScreen(
                navigateToStationInput = { navController.navigate(StationInputDestination.route) },
                navigateToStationUpdate = {
                    navController.navigate("${StationDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = StationInputDestination.route) {
            StationInputScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = StationDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(StationDetailsDestination.stationIdArg) {
                type = NavType.IntType
            })
        ) {
            StationDetailsScreen(
                navigateToEditStation = { navController.navigate("${StationEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = StationEditDestination.routeWithArgs,
            arguments = listOf(navArgument(StationEditDestination.stationIdArg) {
                type = NavType.IntType
            })
        ) {
            StationEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}