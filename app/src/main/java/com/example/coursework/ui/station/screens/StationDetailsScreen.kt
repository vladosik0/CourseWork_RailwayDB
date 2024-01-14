package com.example.coursework.ui.station.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.state.StationUiState
import com.example.coursework.ui.station.viewModels.StationDetailsViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.launch

object StationDetailsDestination : NavigationDestination {
    override val route = "station_details"
    override val titleRes = R.string.row_detail_title
    const val stationIdArg = "stationId"
    val routeWithArgs = "$route/{$stationIdArg}"
}

@Composable
fun StationDetailsScreen(
    navigateToEditStation: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StationDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(StationDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditStation(uiState.value.id) },
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_row_title),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
    ) { innerPadding ->
        StationDetailsBody(
            stationUiState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun StationDetailsBody(
    stationUiState: StationUiState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        StationInputForm(stationUiState = stationUiState, enabled = false)
        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.delete))
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false }
            )
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete_question)) },
        modifier = modifier.padding(16.dp),
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.yes))
            }
        }
    )
}