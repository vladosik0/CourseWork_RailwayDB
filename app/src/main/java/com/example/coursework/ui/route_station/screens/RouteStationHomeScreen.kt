package com.example.coursework.ui.route_station.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.R
import com.example.coursework.data.classes.RouteStation
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.CourseWorkTopAppBar
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.route_station.viewModels.RouteStationHomeViewModel
import kotlinx.coroutines.delay

object RouteStationHomeDestination : NavigationDestination {
    override val route = "route_station_home"
    override val titleRes = R.string.route_station_title
}

/**
 * Entry route for Home screen
 */
@Composable
fun RouteStationHomeScreen(
    navigateToRouteStationInput: () -> Unit,
    navigateToRouteStationUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RouteStationHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.routeStationHomeUiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToRouteStationInput,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.row_input_title)
                )
            }
        },
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(RouteStationHomeDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        RouteStationHomeBody(
            routeStationList = homeUiState.stationList,
            onRouteStationClick = navigateToRouteStationUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun RouteStationHomeBody(
    routeStationList: List<RouteStation>,
    onRouteStationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var isLoading by remember { mutableStateOf(true) }
    if (isLoading) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
            LaunchedEffect(key1 = isLoading) {
                delay(2_000L)
                isLoading = false
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (routeStationList.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_rows_description),
                    style = MaterialTheme.typography.headlineMedium
                )
            } else {
                RouteStationList(
                    routeStationList = routeStationList,
                    onRouteStationClick = { onRouteStationClick(it.id) }
                )
            }
        }
    }
}

@Composable
private fun RouteStationList(
    routeStationList: List<RouteStation>,
    onRouteStationClick: (RouteStation) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = routeStationList, key = {it.id}){ item->
            Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(6.dp)) {
                RouteStationItem(routeStation = item, onRouteStationClick = onRouteStationClick)
            }
        }
    }
}



@Composable
private fun RouteStationItem(
    routeStation: RouteStation,
    onRouteStationClick: (RouteStation) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onRouteStationClick(routeStation) }
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.id_title) + ": " + routeStation.id.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.route_station_route_serial_number_title) + ": " + routeStation.routeSerialNumber.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.route_station_station_id_title) + ": " + routeStation.stationId.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.route_station_route_id_title) + ": " + routeStation.routeId.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
    }
}

