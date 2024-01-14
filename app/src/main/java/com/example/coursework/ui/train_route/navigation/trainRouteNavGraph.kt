package com.example.coursework.ui.train_route.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coursework.ui.train_route.screens.*

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun TrainRouteNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = TrainRouteHomeDestination.route,
        modifier = modifier
    ) {
        composable(route = TrainRouteHomeDestination.route) {
            TrainRouteHomeScreen(
                navigateToTrainRouteInput = { navController.navigate(TrainRouteInputDestination.route) },
                navigateToTrainRouteUpdate = {
                    navController.navigate("${TrainRouteDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = TrainRouteInputDestination.route) {
            TrainRouteInputScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = TrainRouteDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(TrainRouteDetailsDestination.trainRouteIdArg) {
                type = NavType.IntType
            })
        ) {
            TrainRouteDetailsScreen(
                navigateToEditTrainRoute = { navController.navigate("${TrainRouteEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = TrainRouteEditDestination.routeWithArgs,
            arguments = listOf(navArgument(TrainRouteEditDestination.trainRouteIdArg) {
                type = NavType.IntType
            })
        ) {
            TrainRouteEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}