package com.example.coursework.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.CourseWorkViewModel
import com.example.coursework.R

@Composable
fun TopAppBarDropdownMenu() {
    val viewModel: CourseWorkViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val expanded = remember { mutableStateOf(false) }
    Box(
        Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = {
            expanded.value = true
        }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "More Menu",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.station_title)) },
            onClick = {
                expanded.value = false
                viewModel.changeDatabaseTableNameUiState("station")
            }
        )

        Divider()

        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.route_station_title)) },
            onClick = {
                expanded.value = false
                viewModel.changeDatabaseTableNameUiState("route_station")
            }
        )

        Divider()

        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.train_route_title)) },
            onClick = {
                expanded.value = false
                viewModel.changeDatabaseTableNameUiState("train_route")
            }
        )

        Divider()

        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.train_title)) },
            onClick = {
                expanded.value = false
                viewModel.changeDatabaseTableNameUiState("train")
            }
        )

        Divider()

        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.seat_title)) },
            onClick = {
                expanded.value = false
                viewModel.changeDatabaseTableNameUiState("seat")
            }
        )

        Divider()

        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.ticket_title)) },
            onClick = {
                expanded.value = false
                viewModel.changeDatabaseTableNameUiState("ticket")
            }
        )

        Divider()

        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.wagon_title)) },
            onClick = {
                expanded.value = false
                viewModel.changeDatabaseTableNameUiState("wagon")
            }
        )
        Divider()
        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.about_title)) },
            onClick = {
                expanded.value = false
                viewModel.changeDatabaseTableNameUiState("About project")
            }
        )
    }
}