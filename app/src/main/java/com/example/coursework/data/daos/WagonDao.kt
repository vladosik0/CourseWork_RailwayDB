package com.example.coursework.data.daos

import androidx.room.*
import com.example.coursework.data.classes.Wagon
import kotlinx.coroutines.flow.Flow

@Dao
interface WagonDao {
    @Query("SELECT * from wagon")
    fun getAllItems(): Flow<List<Wagon>>

    @Query("SELECT * from wagon WHERE id = :id")
    fun getItem(id: Int): Flow<Wagon>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Wagon)

    @Update
    suspend fun update(item: Wagon)

    @Delete
    suspend fun delete(item: Wagon)
}