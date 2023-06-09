package com.example.coursework.ui.wagon.screens

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
import com.example.coursework.data.classes.Wagon
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.wagon.viewModels.WagonHomeViewModel
import com.example.coursework.R

object WagonHomeDestination : NavigationDestination {
    override val route = "wagon_home"
    override val titleRes = R.string.wagon_title
}

/**
 * Entry route for Home screen
 */
@Composable
fun WagonHomeScreen(
    navigateToWagonInput: () -> Unit,
    navigateToWagonUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WagonHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.wagonHomeUiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToWagonInput,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.row_input_title),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
    ) { innerPadding ->
        WagonHomeBody(
            wagonList = homeUiState.wagonList,
            onWagonClick = navigateToWagonUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun WagonHomeBody(
    wagonList: List<Wagon>,
    onWagonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (wagonList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_rows_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            WagonList(wagonList = wagonList, onWagonClick = { onWagonClick(it.id) })
        }
    }
}

@Composable
private fun WagonList(
    wagonList: List<Wagon>,
    onWagonClick: (Wagon) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = wagonList, key = {it.id}){ item->
            Card(modifier = Modifier.padding(8.dp), elevation = 6.dp) {
                WagonItem(wagon = item, onWagonClick = onWagonClick)
            }
        }
    }
}



@Composable
private fun WagonItem(
    wagon: Wagon,
    onWagonClick: (Wagon) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onWagonClick(wagon) }
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.id_title) + ": " + wagon.id.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.wagon_wagon_number_title) + ": " + wagon.wagonNumber.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.wagon_wagon_type_title) + ": " + wagon.wagonType,
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.wagon_train_id_title) + ": " + wagon.trainId.toString(),
            modifier = Modifier.padding(4.dp),
            color = Color.Black, textAlign = TextAlign.Center
        )
    }
}

