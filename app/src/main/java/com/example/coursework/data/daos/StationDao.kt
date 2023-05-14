package com.example.coursework.data.daos

import androidx.room.*
import com.example.coursework.data.classes.Station
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {
    @Query("SELECT * from station")
    fun getAllItems(): Flow<List<Station>>

    @Query("SELECT * from station WHERE id = :id")
    fun getItem(id: Int): Flow<Station>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Station)

    @Update
    suspend fun update(item: Station)

    @Delete
    suspend fun delete(item: Station)
}