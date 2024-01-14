package com.example.coursework.ui.train_route.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.train_route.viewModels.TrainRouteEditViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.launch

object TrainRouteEditDestination : NavigationDestination {
    override val route = "station_edit"
    override val titleRes = R.string.edit_row_title
    const val trainRouteIdArg = "trainRouteId"
    val routeWithArgs = "$route/{$trainRouteIdArg}"
}

@Composable
fun TrainRouteEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrainRouteEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(TrainRouteEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        TrainRouteInputBody(
            trainRouteUiState = viewModel.trainRouteUiState,
            onTrainRouteValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTrainRoute()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}