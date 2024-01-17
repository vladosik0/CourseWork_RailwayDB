package com.example.coursework.ui.train.screens

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
import com.example.coursework.data.classes.Train
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.train.viewModels.TrainHomeViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar

object TrainHomeDestination : NavigationDestination {
    override val route = "train_home"
    override val titleRes = R.string.train_title
}

/**
 * Entry route for Home screen
 */
@Composable
fun TrainHomeScreen(
    navigateToTrainInput: () -> Unit,
    navigateToTrainUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrainHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.trainHomeUiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToTrainInput,
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
                title = stringResource(TrainHomeDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        TrainHomeBody(
            trainList = homeUiState.trainList,
            onTrainClick = navigateToTrainUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun TrainHomeBody(
    trainList: List<Train>,
    onTrainClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (trainList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_rows_description),
                style = MaterialTheme.typography.headlineMedium
            )
        } else {
            TrainList(trainList = trainList, onTrainClick = { onTrainClick(it.id) })
        }
    }
}

@Composable
private fun TrainList(
    trainList: List<Train>,
    onTrainClick: (Train) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = trainList, key = {it.id}){ item->
            Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(10.dp)) {
                TrainItem(train = item, onTrainClick = onTrainClick)
            }
        }
    }
}



@Composable
private fun TrainItem(
    train: Train,
    onTrainClick: (Train) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTrainClick(train) }
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.id_title) + ": " + train.id.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.train_number_title) + ": " + train.trainNumber.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.train_departure_date_title) + ": " + train.departureDate,
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.train_arrival_date_title) + ": " + train.arrivalDate,
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.route_id_title) + ": " + train.routeId.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
    }
}

