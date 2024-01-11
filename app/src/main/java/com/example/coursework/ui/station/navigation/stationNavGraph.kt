package com.example.coursework.ui.station.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    screenContent: MutableState<String>,
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
                screenContent = screenContent,
                navigateToStationInput = { navController.navigate(StationInputDestination.route) },
                navigateToStationUpdate = {
                    navController.navigate("${StationDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = StationInputDestination.route) {
            StationInputScreen(
                screenContent = screenContent,
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
                screenContent = screenContent,
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
                screenContent = screenContent,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}