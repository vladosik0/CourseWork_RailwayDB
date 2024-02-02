package com.example.coursework.ui.train.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.state.TrainUiState
import com.example.coursework.ui.train.viewModels.TrainInputViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

object TrainInputDestination : NavigationDestination {
    override val route = "train_input"
    override val titleRes = R.string.row_input_title
}

@Composable
fun TrainInputScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: TrainInputViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(TrainInputDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        TrainInputBody(
            trainUiState = viewModel.trainUiState,
            onTrainValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    val toastMessage = viewModel.validateTrainInput()
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    if (toastMessage == "Row added successfully.") {
                        navigateBack()
                    }
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun TrainInputBody(
    trainUiState: TrainUiState,
    onTrainValueChange: (TrainUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        TrainInputForm(trainUiState = trainUiState, onValueChange = onTrainValueChange)
        Button(
            onClick = onSaveClick,
            enabled = trainUiState.actionEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun TrainInputForm(
    trainUiState: TrainUiState,
    modifier: Modifier = Modifier,
    onValueChange: (TrainUiState) -> Unit = {},
    enabled: Boolean = true
) {
    val openDatePickerDialog = remember { mutableStateOf(false) }
    val isDepartureDatePicked = remember { mutableStateOf(false) }
    if (openDatePickerDialog.value) {
        MyDatePickerDialog(
            isDepartureDatePicked = isDepartureDatePicked.value,
            trainUiState = trainUiState,
            onDateSelected = onValueChange,
            onDismiss = { openDatePickerDialog.value = false }
        )
    }
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = trainUiState.trainNumber,
            onValueChange = { onValueChange(trainUiState.copy(trainNumber = it)) },
            label = { Text(stringResource(R.string.train_number_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = trainUiState.departureDate,
            readOnly = true,
            onValueChange = {},
            label = { Text(stringResource(R.string.train_departure_date_title)) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (enabled) {
                            openDatePickerDialog.value = true
                            isDepartureDatePicked.value = true
                        }
                    },
                ) {
                    Icon(imageVector = Icons.Rounded.DateRange, contentDescription = null)
                }
            },
            singleLine = true
        )

        OutlinedTextField(
            value = trainUiState.arrivalDate,
            readOnly = true,
            onValueChange = {},
            label = { Text(stringResource(R.string.train_arrival_date_title)) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (enabled) {
                            openDatePickerDialog.value = true
                            isDepartureDatePicked.value = false
                        }
                    },
                ) {
                    Icon(imageVector = Icons.Rounded.DateRange, contentDescription = null)
                }
            },
            singleLine = true
        )
        OutlinedTextField(
            value = trainUiState.routeId,
            onValueChange = { onValueChange(trainUiState.copy(routeId = it)) },
            label = { Text(stringResource(R.string.route_id_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    isDepartureDatePicked: Boolean,
    trainUiState: TrainUiState,
    onDateSelected: (TrainUiState) -> Unit = {},
    onDismiss: () -> Unit
) {
    val state = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (isDepartureDatePicked) {
                        onDateSelected(
                            trainUiState.copy(
                                departureDate = SimpleDateFormat("dd/MM/yyyy").format(
                                    state.selectedDateMillis
                                )
                            )
                        )
                    } else {
                        onDateSelected(
                            trainUiState.copy(
                                arrivalDate = SimpleDateFormat("dd/MM/yyyy").format(
                                    state.selectedDateMillis
                                )
                            )
                        )
                    }
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("CANCEL")
            }
        }
    ) {
        DatePicker(
            state = state
        )
    }
}