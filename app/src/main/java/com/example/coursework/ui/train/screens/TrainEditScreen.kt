package com.example.coursework.ui.train.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.train.TrainTopAppBar
import com.example.coursework.ui.train.viewModels.TrainEditViewModel
import com.example.coursework.R
import kotlinx.coroutines.launch

object TrainEditDestination : NavigationDestination {
    override val route = "train_edit"
    override val titleRes = R.string.edit_row_title
    const val trainIdArg = "trainId"
    val routeWithArgs = "$route/{$trainIdArg}"
}

@Composable
fun TrainEditScreen(
    screenContent: MutableState<String>,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrainEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TrainTopAppBar(
                screenContent = screenContent,
                title = stringResource(TrainEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        TrainInputBody(
            trainUiState = viewModel.trainUiState,
            onTrainValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTrain()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}