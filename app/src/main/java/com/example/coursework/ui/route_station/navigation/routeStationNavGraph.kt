package com.example.coursework.ui.route_station.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coursework.ui.route_station.screens.*

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun RouteStationNavHost(
    screenContent: MutableState<String>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = RouteStationHomeDestination.route,
        modifier = modifier
    ) {
        composable(route = RouteStationHomeDestination.route) {
            RouteStationHomeScreen(
                screenContent = screenContent,
                navigateToRouteStationInput = { navController.navigate(RouteStationInputDestination.route) },
                navigateToRouteStationUpdate = {
                    navController.navigate("${RouteStationDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = RouteStationInputDestination.route) {
            RouteStationInputScreen(
                screenContent = screenContent,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = RouteStationDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(RouteStationDetailsDestination.routeStationIdArg) {
                type = NavType.IntType
            })
        ) {
            RouteStationDetailsScreen(
                screenContent = screenContent,
                navigateToEditRouteStation = { navController.navigate("${RouteStationEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = RouteStationEditDestination.routeWithArgs,
            arguments = listOf(navArgument(RouteStationEditDestination.routeStationIdArg) {
                type = NavType.IntType
            })
        ) {
            RouteStationEditScreen(
                screenContent = screenContent,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}