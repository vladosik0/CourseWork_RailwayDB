package com.example.coursework.ui.station.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.data.classes.Station
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.station.viewModels.StationHomeViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar

object StationHomeDestination : NavigationDestination {
    override val route = "station_home"
    override val titleRes = R.string.station_title
}

/**
 * Entry route for Home screen
 */
@Composable
fun StationHomeScreen(
    navigateToStationInput: () -> Unit,
    navigateToStationUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StationHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.stationHomeUiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToStationInput,
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
                title = stringResource(StationHomeDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        StationHomeBody(
            stationList = homeUiState.stationList,
            onStationClick = navigateToStationUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun StationHomeBody(
    stationList: List<Station>,
    onStationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (stationList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_rows_description),
                style = MaterialTheme.typography.headlineMedium
            )
        } else {
            StationList(stationList = stationList, onStationClick = { onStationClick(it.id) })
        }
    }
}

@Composable
private fun StationList(
    stationList: List<Station>,
    onStationClick: (Station) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = stationList, key = {it.id}){ item->
            Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(6.dp)) {
                StationItem(station = item, onStationClick = onStationClick)
            }
        }
    }
}



@Composable
private fun StationItem(
    station: Station,
    onStationClick: (Station) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onStationClick(station) }
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.id_title) + ": " + station.id.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.station_name_title) + ": " + station.stationName,
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
    }
}



