package com.example.coursework

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.coursework.data.AppContainer
import com.example.coursework.data.AppDataContainer
import com.example.coursework.data.repositories.DatabaseTableNameRepository

private const val TABLE_NAME = "table_name"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = TABLE_NAME
)
class RailwayApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    lateinit var databaseTableNameRepository: DatabaseTableNameRepository
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        databaseTableNameRepository = DatabaseTableNameRepository(dataStore)
    }
}