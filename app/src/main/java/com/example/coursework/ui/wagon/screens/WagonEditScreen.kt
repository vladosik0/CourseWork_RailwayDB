package com.example.coursework.ui.wagon.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.AppViewModelProvider
import com.example.coursework.ui.NavigationDestination
import com.example.coursework.ui.wagon.viewModels.WagonEditViewModel
import com.example.coursework.R
import com.example.coursework.ui.CourseWorkTopAppBar
import kotlinx.coroutines.launch

object WagonEditDestination : NavigationDestination {
    override val route = "wagon_edit"
    override val titleRes = R.string.edit_row_title
    const val wagonIdArg = "wagonId"
    val routeWithArgs = "$route/{$wagonIdArg}"
}

@Composable
fun WagonEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WagonEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CourseWorkTopAppBar(
                title = stringResource(WagonEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        WagonInputBody(
            wagonUiState = viewModel.wagonUiState,
            onWagonValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateWagon()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}