package com.example.coursework.ui.wagon.screens

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
import com.example.coursework.ui.state.WagonUiState
import com.example.coursework.ui.wagon.WagonTopAppBar
import com.example.coursework.ui.wagon.viewModels.WagonInputViewModel
import com.example.coursework.R
import kotlinx.coroutines.launch

object WagonInputDestination : NavigationDestination {
    override val route = "wagon_input"
    override val titleRes = R.string.row_input_title
}

@Composable
fun WagonInputScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: WagonInputViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            WagonTopAppBar(
                title = stringResource(WagonInputDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        WagonInputBody(
            wagonUiState = viewModel.wagonUiState,
            onWagonValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveWagon()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun WagonInputBody(
    wagonUiState: WagonUiState,
    onWagonValueChange: (WagonUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        WagonInputForm(wagonUiState = wagonUiState, onValueChange = onWagonValueChange)
        Button(
            onClick = onSaveClick,
            enabled = wagonUiState.actionEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun WagonInputForm(
    wagonUiState: WagonUiState,
    modifier: Modifier = Modifier,
    onValueChange: (WagonUiState) -> Unit = {},
    enabled: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = wagonUiState.wagonNumber,
            onValueChange = { onValueChange(wagonUiState.copy(wagonNumber = it)) },
            label = { Text(stringResource(R.string.wagon_wagon_number_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = wagonUiState.wagonType,
            onValueChange = { onValueChange(wagonUiState.copy(wagonType = it)) },
            label = { Text(stringResource(R.string.wagon_wagon_type_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = wagonUiState.trainId,
            onValueChange = { onValueChange(wagonUiState.copy(trainId = it)) },
            label = { Text(stringResource(R.string.wagon_train_id_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}