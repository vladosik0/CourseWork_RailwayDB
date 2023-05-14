package com.example.coursework.ui.seat.screens

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
import com.example.coursework.ui.seat.SeatTopAppBar
import com.example.coursework.ui.seat.viewModels.SeatInputViewModel
import com.example.coursework.ui.state.SeatUiState
import com.example.coursework.R
import kotlinx.coroutines.launch

object SeatInputDestination : NavigationDestination {
    override val route = "seat_input"
    override val titleRes = R.string.row_input_title
}

@Composable
fun SeatInputScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: SeatInputViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            SeatTopAppBar(
                title = stringResource(SeatInputDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        SeatInputBody(
            seatUiState = viewModel.seatUiState,
            onSeatValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveSeat()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SeatInputBody(
    seatUiState: SeatUiState,
    onSeatValueChange: (SeatUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        SeatInputForm(seatUiState = seatUiState, onValueChange = onSeatValueChange)
        Button(
            onClick = onSaveClick,
            enabled = seatUiState.actionEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun SeatInputForm(
    seatUiState: SeatUiState,
    modifier: Modifier = Modifier,
    onValueChange: (SeatUiState) -> Unit = {},
    enabled: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = seatUiState.seatNumber,
            onValueChange = { onValueChange(seatUiState.copy(seatNumber = it)) },
            label = { Text(stringResource(R.string.seat_seat_number_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = seatUiState.wagonId,
            onValueChange = { onValueChange(seatUiState.copy(wagonId = it)) },
            label = { Text(stringResource(R.string.seat_wagon_id_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}