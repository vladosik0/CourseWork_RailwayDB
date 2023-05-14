package com.example.coursework.ui.train_route.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.state.TrainRouteUiState
import com.example.coursework.ui.train_route.TrainRouteTopAppBar
import com.example.coursework.ui.train_route.viewModels.TrainRouteInputViewModel
import com.example.coursework.R
import kotlinx.coroutines.launch

object TrainRouteInputDestination : NavigationDestination {
    override val route = "train_route_input"
    override val titleRes = R.string.row_input_title
}

@Composable
fun TrainRouteInputScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: TrainRouteInputViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TrainRouteTopAppBar(
                title = stringResource(TrainRouteInputDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        TrainRouteInputBody(
            trainRouteUiState = viewModel.trainRouteUiState,
            onTrainRouteValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveTrainRoute()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun TrainRouteInputBody(
    trainRouteUiState: TrainRouteUiState,
    onTrainRouteValueChange: (TrainRouteUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        TrainRouteInputForm(trainRouteUiState = trainRouteUiState, onValueChange = onTrainRouteValueChange)
        Button(
            onClick = onSaveClick,
            enabled = trainRouteUiState.actionEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun TrainRouteInputForm(
    trainRouteUiState: TrainRouteUiState,
    modifier: Modifier = Modifier,
    onValueChange: (TrainRouteUiState) -> Unit = {},
    enabled: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = trainRouteUiState.routeName,
            onValueChange = { onValueChange(trainRouteUiState.copy(routeName = it)) },
            label = { Text(stringResource(R.string.train_route_route_name_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}