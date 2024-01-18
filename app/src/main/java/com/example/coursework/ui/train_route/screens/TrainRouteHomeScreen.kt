package com.example.coursework.ui.train_route.screens

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
import com.example.coursework.data.classes.TrainRoute
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.train_route.viewModels.TrainRouteHomeViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.delay

object TrainRouteHomeDestination : NavigationDestination {
    override val route = "train_route_home"
    override val titleRes = R.string.train_route_title
}

/**
 * Entry route for Home screen
 */
@Composable
fun TrainRouteHomeScreen(
    navigateToTrainRouteInput: () -> Unit,
    navigateToTrainRouteUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrainRouteHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.trainRouteHomeUiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToTrainRouteInput,
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
                title = stringResource(TrainRouteHomeDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        TrainRouteHomeBody(
            trainRouteList = homeUiState.trainRouteList,
            onTrainRouteClick = navigateToTrainRouteUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun TrainRouteHomeBody(
    trainRouteList: List<TrainRoute>,
    onTrainRouteClick: (Int) -> Unit,
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
            if (trainRouteList.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_rows_description),
                    style = MaterialTheme.typography.headlineMedium
                )
            } else {
                TrainRouteList(trainRouteList = trainRouteList, onTrainRouteClick = { onTrainRouteClick(it.id) })
            }
        }
    }
}

@Composable
private fun TrainRouteList(
    trainRouteList: List<TrainRoute>,
    onTrainRouteClick: (TrainRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = trainRouteList, key = {it.id}){ item->
            Card(modifier = Modifier.padding(4.dp), elevation = CardDefaults.cardElevation(6.dp)) {
                TrainRouteItem(trainRoute = item, onTrainRouteClick = onTrainRouteClick)
            }
        }
    }
}



@Composable
private fun TrainRouteItem(
    trainRoute: TrainRoute,
    onTrainRouteClick: (TrainRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTrainRouteClick(trainRoute) }
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.id_title) + ": " + trainRoute.id.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.train_route_route_name_title) + ": " + trainRoute.routeName,
            modifier = Modifier.padding(2.dp),
            textAlign = TextAlign.Start
        )
    }

}

