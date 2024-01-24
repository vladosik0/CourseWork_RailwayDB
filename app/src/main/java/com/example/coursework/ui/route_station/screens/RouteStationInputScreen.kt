package com.example.coursework.ui.route_station.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.route_station.viewModels.RouteStationInputViewModel
import com.example.coursework.ui.state.RouteStationUiState
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar

object RouteStationInputDestination : NavigationDestination {
    override val route = "route_station_input"
    override val titleRes = R.string.row_input_title
}

@Composable
fun RouteStationInputScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: RouteStationInputViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(RouteStationInputDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        RouteStationInputBody(
            routeStationUiState = viewModel.routeStationUiState,
            onRouteStationValueChange = viewModel::updateUiState,
            onSaveClick = {
                val toastMessage = viewModel.validateRouteStationInput()
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                if (toastMessage == "Row added successfully.") {
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun RouteStationInputBody(
    routeStationUiState: RouteStationUiState,
    onRouteStationValueChange: (RouteStationUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        RouteStationInputForm(
            routeStationUiState = routeStationUiState,
            onValueChange = onRouteStationValueChange
        )
        Button(
            onClick = onSaveClick,
            enabled = routeStationUiState.actionEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun RouteStationInputForm(
    routeStationUiState: RouteStationUiState,
    modifier: Modifier = Modifier,
    onValueChange: (RouteStationUiState) -> Unit = {},
    enabled: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = routeStationUiState.routeSerialNumber,
            onValueChange = { onValueChange(routeStationUiState.copy(routeSerialNumber = it)) },
            label = { Text(stringResource(R.string.route_station_route_serial_number_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = routeStationUiState.stationId,
            onValueChange = { onValueChange(routeStationUiState.copy(stationId = it)) },
            label = { Text(stringResource(R.string.route_station_station_id_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = routeStationUiState.routeId,
            onValueChange = { onValueChange(routeStationUiState.copy(routeId = it)) },
            label = { Text(stringResource(R.string.route_station_route_id_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}