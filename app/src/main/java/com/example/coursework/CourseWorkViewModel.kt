package com.example.coursework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.data.repositories.DatabaseTableNameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseWorkViewModel(
    private val databaseTableNameRepository: DatabaseTableNameRepository
) : ViewModel() {
    private val _databaseTableNameUiState = MutableStateFlow(DatabaseTableNameUiState())
    val databaseTableNameUiState: StateFlow<DatabaseTableNameUiState> =
        _databaseTableNameUiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            databaseTableNameRepository.tableName.collect {
                _databaseTableNameUiState.value = DatabaseTableNameUiState(it)
            }
        }
    }

    fun changeDatabaseTableNameUiState(tableName: String) {
        _databaseTableNameUiState.value = DatabaseTableNameUiState(tableName = tableName)
        viewModelScope.launch(Dispatchers.IO) {
            databaseTableNameRepository.saveTableName(tableName = tableName)
        }
    }
}

data class DatabaseTableNameUiState(val tableName: String = "")