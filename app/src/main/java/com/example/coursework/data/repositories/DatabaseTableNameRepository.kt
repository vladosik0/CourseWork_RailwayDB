package com.example.coursework.data.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DatabaseTableNameRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val TABLE_NAME = stringPreferencesKey("table_name")
        const val TAG = "DatabaseTableNameRepo"
    }

    suspend fun saveTableName(tableName: String) {
        dataStore.edit { string ->
            string[TABLE_NAME] = tableName
        }
    }

    val tableName: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences. ", it)
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[TABLE_NAME] ?: "train"
        }
}