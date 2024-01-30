package com.example.coursework.ui.seat.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.seat.viewModels.SeatDetailsViewModel
import com.example.coursework.ui.state.SeatUiState
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.launch

object SeatDetailsDestination : NavigationDestination {
    override val route = "seat_details"
    override val titleRes = R.string.row_detail_title
    const val seatIdArg = "seatId"
    val routeWithArgs = "$route/{$seatIdArg}"
}

@Composable
fun SeatDetailsScreen(
    navigateToEditSeat: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SeatDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(SeatDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditSeat(uiState.value.id) },
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_row_title)
                )
            }
        },
    ) { innerPadding ->
        SeatDetailsBody(
            seatUiState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    val toastMessage = viewModel.validateSeatDeletion()
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    if (toastMessage == "Row deleted successfully.") {
                        navigateBack()
                    }
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun SeatDetailsBody(
    seatUiState: SeatUiState,
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
        SeatInputForm(seatUiState = seatUiState, enabled = false)
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