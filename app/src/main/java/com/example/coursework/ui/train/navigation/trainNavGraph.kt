package com.example.coursework.ui.train.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coursework.ui.train.screens.*

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun TrainNavHost(
    screenContent: MutableState<String>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = TrainHomeDestination.route,
        modifier = modifier
    ) {
        composable(route = TrainHomeDestination.route) {
            TrainHomeScreen(
                screenContent = screenContent,
                navigateToTrainInput = { navController.navigate(TrainInputDestination.route) },
                navigateToTrainUpdate = {
                    navController.navigate("${TrainDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = TrainInputDestination.route) {
            TrainInputScreen(
                screenContent = screenContent,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = TrainDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(TrainDetailsDestination.trainIdArg) {
                type = NavType.IntType
            })
        ) {
            TrainDetailsScreen(
                screenContent = screenContent,
                navigateToEditTrain = { navController.navigate("${TrainEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = TrainEditDestination.routeWithArgs,
            arguments = listOf(navArgument(TrainEditDestination.trainIdArg) {
                type = NavType.IntType
            })
        ) {
            TrainEditScreen(
                screenContent = screenContent,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}