package com.example.coursework.ui.ticket.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.state.TicketUiState
import com.example.coursework.ui.ticket.viewModels.TicketInputViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.launch

object TicketInputDestination : NavigationDestination {
    override val route = "ticket_input"
    override val titleRes = R.string.row_input_title
}

@Composable
fun TicketInputScreen(
    screenContent: MutableState<String>,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: TicketInputViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                screenContent = screenContent,
                title = stringResource(TicketInputDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        TicketInputBody(
            ticketUiState = viewModel.ticketUiState,
            onTicketValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveTicket()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun TicketInputBody(
    ticketUiState: TicketUiState,
    onTicketValueChange: (TicketUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    availableEnabled: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        TicketInputForm(
            ticketUiState = ticketUiState,
            onValueChange = onTicketValueChange,
            availableEnabled = availableEnabled
        )
        Button(
            onClick = onSaveClick,
            enabled = ticketUiState.actionEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun TicketInputForm(
    ticketUiState: TicketUiState,
    modifier: Modifier = Modifier,
    onValueChange: (TicketUiState) -> Unit = {},
    enabled: Boolean = true,
    availableEnabled:Boolean = false
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = ticketUiState.price,
            onValueChange = { onValueChange(ticketUiState.copy(price = it)) },
            label = { Text(stringResource(R.string.ticket_price_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = ticketUiState.available.toString(),
            onValueChange = { onValueChange(ticketUiState.copy(available = it.toBoolean())) },
            label = { Text(stringResource(R.string.ticket_available_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = availableEnabled,
            singleLine = true
        )
        OutlinedTextField(
            value = ticketUiState.seatId,
            onValueChange = { onValueChange(ticketUiState.copy(seatId = it)) },
            label = { Text(stringResource(R.string.ticket_seat_id_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = ticketUiState.startStationId,
            onValueChange = { onValueChange(ticketUiState.copy(startStationId = it)) },
            label = { Text(stringResource(R.string.ticket_start_station_id_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = ticketUiState.endStationId,
            onValueChange = { onValueChange(ticketUiState.copy(endStationId = it)) },
            label = { Text(stringResource(R.string.ticket_end_station_id_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}