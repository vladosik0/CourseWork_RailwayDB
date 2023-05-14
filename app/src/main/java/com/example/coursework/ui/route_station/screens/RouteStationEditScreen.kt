package com.example.coursework.ui.route_station.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.route_station.RouteStationTopAppBar
import com.example.coursework.ui.route_station.viewModels.RouteStationEditViewModel
import com.example.coursework.R
import kotlinx.coroutines.launch

object RouteStationEditDestination : NavigationDestination {
    override val route = "route_station_edit"
    override val titleRes = R.string.edit_row_title
    const val routeStationIdArg = "routeStationId"
    val routeWithArgs = "$route/{$routeStationIdArg}"
}

@Composable
fun RouteStationEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RouteStationEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            RouteStationTopAppBar(
                title = stringResource(RouteStationEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        RouteStationInputBody(
            routeStationUiState = viewModel.routeStationUiState,
            onRouteStationValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateRouteStation()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}