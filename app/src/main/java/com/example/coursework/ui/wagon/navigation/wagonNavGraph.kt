package com.example.coursework.ui.wagon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coursework.ui.wagon.screens.*

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun WagonNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = WagonHomeDestination.route,
        modifier = modifier
    ) {
        composable(route = WagonHomeDestination.route) {
            WagonHomeScreen(
                navigateToWagonInput = { navController.navigate(WagonInputDestination.route) },
                navigateToWagonUpdate = {
                    navController.navigate("${WagonDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = WagonInputDestination.route) {
            WagonInputScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = WagonDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(WagonDetailsDestination.wagonIdArg) {
                type = NavType.IntType
            })
        ) {
            WagonDetailsScreen(
                navigateToEditWagon = { navController.navigate("${WagonEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = WagonEditDestination.routeWithArgs,
            arguments = listOf(navArgument(WagonEditDestination.wagonIdArg) {
                type = NavType.IntType
            })
        ) {
            WagonEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}