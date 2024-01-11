package com.example.coursework.ui.ticket.screens

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
import com.example.coursework.ui.ticket.TicketTopAppBar
import com.example.coursework.ui.ticket.viewModels.TicketEditViewModel
import com.example.coursework.R
import kotlinx.coroutines.launch

object TicketEditDestination : NavigationDestination {
    override val route = "ticket_edit"
    override val titleRes = R.string.edit_row_title
    const val ticketIdArg = "ticketId"
    val routeWithArgs = "$route/{$ticketIdArg}"
}

@Composable
fun TicketEditScreen(
    screenContent: MutableState<String>,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TicketEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TicketTopAppBar(
                screenContent = screenContent,
                title = stringResource(TicketEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        TicketInputBody(
            ticketUiState = viewModel.ticketUiState,
            onTicketValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTicket()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding),
            availableEnabled = true
        )
    }
}