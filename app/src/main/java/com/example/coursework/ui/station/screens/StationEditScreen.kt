package com.example.coursework.ui.station.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.station.StationTopAppBar
import com.example.coursework.ui.station.viewModels.StationEditViewModel
import com.example.coursework.R
import kotlinx.coroutines.launch

object StationEditDestination : NavigationDestination {
    override val route = "station_edit"
    override val titleRes = R.string.edit_row_title
    const val stationIdArg = "stationId"
    val routeWithArgs = "$route/{$stationIdArg}"
}

@Composable
fun StationEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StationEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            StationTopAppBar(
                title = stringResource(StationEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        StationInputBody(
            stationUiState = viewModel.stationUiState,
            onStationValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateStation()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}