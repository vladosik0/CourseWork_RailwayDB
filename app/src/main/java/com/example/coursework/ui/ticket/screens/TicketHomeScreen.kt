package com.example.coursework.ui.ticket.screens

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
import com.example.coursework.data.classes.Ticket
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.ticket.viewModels.TicketHomeViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.delay

object TicketHomeDestination : NavigationDestination {
    override val route = "ticket_home"
    override val titleRes = R.string.ticket_title
}

/**
 * Entry route for Home screen
 */
@Composable
fun TicketHomeScreen(
    navigateToTicketInput: () -> Unit,
    navigateToTicketUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TicketHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.ticketHomeUiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToTicketInput,
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
                title = stringResource(TicketHomeDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        TicketHomeBody(
            ticketList = homeUiState.ticketList,
            onTicketClick = navigateToTicketUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun TicketHomeBody(
    ticketList: List<Ticket>,
    onTicketClick: (Int) -> Unit,
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
            if (ticketList.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_rows_description),
                    style = MaterialTheme.typography.headlineMedium
                )
            } else {
                TicketList(ticketList = ticketList, onTicketClick = { onTicketClick(it.id) })
            }
        }
    }
}

@Composable
private fun TicketList(
    ticketList: List<Ticket>,
    onTicketClick: (Ticket) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = ticketList, key = { it.id }) { item ->
            Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(6.dp)) {
                TicketItem(ticket = item, onTicketClick = onTicketClick)
            }

        }
    }
}


@Composable
private fun TicketItem(
    ticket: Ticket,
    onTicketClick: (Ticket) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTicketClick(ticket) }
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.id_title) + ": " + ticket.id.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.ticket_price_title) + ": " + ticket.price.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.ticket_start_station_id_title) + ": " + ticket.startStationId.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.ticket_end_station_id_title) + ": " + ticket.endStationId.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.ticket_available_title) + ": " + ticket.available.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.ticket_seat_id_title) + ": " + ticket.seatId.toString(),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center
        )
    }
}

