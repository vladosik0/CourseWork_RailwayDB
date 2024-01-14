package com.example.coursework.ui.seat.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.data.classes.Seat
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.seat.viewModels.SeatHomeViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar

object SeatHomeDestination : NavigationDestination {
    override val route = "seat_home"
    override val titleRes = R.string.seat_title
}

/**
 * Entry route for Home screen
 */
@Composable
fun SeatHomeScreen(
    navigateToSeatInput: () -> Unit,
    navigateToSeatUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SeatHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.seatHomeUiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToSeatInput,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.row_input_title),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(SeatHomeDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        SeatHomeBody(
            seatList = homeUiState.seatList,
            onSeatClick = navigateToSeatUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun SeatHomeBody(
    seatList: List<Seat>,
    onSeatClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (seatList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_rows_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            SeatList(seatList = seatList, onSeatClick = { onSeatClick(it.id) })
        }
    }
}

@Composable
private fun SeatList(
    seatList: List<Seat>,
    onSeatClick: (Seat) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = seatList, key = {it.id}){ item->
            Card(modifier = Modifier.padding(8.dp), elevation = 6.dp) {
                SeatItem(seat = item, onSeatClick = onSeatClick)
            }
        }
    }
}


@Composable
private fun SeatItem(
    seat: Seat,
    onSeatClick: (Seat) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSeatClick(seat) }
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.id_title) + ": " + seat.id.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.seat_seat_number_title) + ": " + seat.seatNumber.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.seat_wagon_id_title) + ": " + seat.wagonId.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
    }
}