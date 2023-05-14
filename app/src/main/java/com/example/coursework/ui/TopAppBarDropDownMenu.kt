package com.example.coursework.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coursework.R

@Composable
fun TopAppBarDropdownMenu(screenContent: MutableState<String>) {
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
                tint = Color.White
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(onClick = {
            expanded.value = false
            screenContent.value = "station"
        }) {
            Text(stringResource(id = R.string.station_title))
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            screenContent.value = "route_station"
        }) {
            Text(stringResource(id = R.string.route_station_title))
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            screenContent.value = "train_route"
        }) {
            Text(stringResource(id = R.string.train_route_title))
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            screenContent.value = "train"
        }) {
            Text(stringResource(id = R.string.train_title))
        }
        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            screenContent.value = "seat"
        }) {
            Text(stringResource(id = R.string.seat_title))
        }
        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            screenContent.value = "ticket"
        }) {
            Text(stringResource(id = R.string.ticket_title))
        }
        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            screenContent.value = "wagon"
        }) {
            Text(stringResource(id = R.string.wagon_title))
        }
    }
}