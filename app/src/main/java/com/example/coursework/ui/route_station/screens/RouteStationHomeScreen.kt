package com.example.coursework.ui.route_station.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

object RouteStationHomeDestination : NavigationDestination {
    override val route = "route_station_home"
    override val titleRes = R.string.route_station_title
}

/**
 * Entry route for Home screen
 */
@Composable
fun RouteStationHomeScreen(
    screenContent: MutableState<String>,
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
                    contentDescription = stringResource(R.string.row_input_title),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        topBar = {
            CourseWorkTopAppBar(
                screenContent = screenContent,
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (routeStationList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_rows_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            RouteStationList(routeStationList = routeStationList, onRouteStationClick = { onRouteStationClick(it.id) })
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
            Card(modifier = Modifier.padding(8.dp), elevation = 6.dp) {
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
            color = Color.Black, textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.route_station_route_serial_number_title) + ": " + routeStation.routeSerialNumber.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.route_station_station_id_title) + ": " + routeStation.stationId.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.route_station_route_id_title) + ": " + routeStation.routeId.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
    }
}

