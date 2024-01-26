package com.example.coursework.ui.seat.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.seat.viewModels.SeatEditViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.launch

object SeatEditDestination : NavigationDestination {
    override val route = "seat_edit"
    override val titleRes = R.string.edit_row_title
    const val seatIdArg = "seatId"
    val routeWithArgs = "$route/{$seatIdArg}"
}

@Composable
fun SeatEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SeatEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(SeatEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        SeatInputBody(
            seatUiState = viewModel.seatUiState,
            onSeatValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    val toastMessage = viewModel.updateSeat()
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    if (toastMessage == "Row updated successfully.") {
                        navigateBack()
                    }
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}