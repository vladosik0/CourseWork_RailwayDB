package com.example.coursework.data.daos

import androidx.room.*
import com.example.coursework.data.classes.Train
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainDao {
    @Query("SELECT * from train")
    fun getAllItems(): Flow<List<Train>>

    @Query("SELECT * from train WHERE id = :id")
    fun getItem(id: Int): Flow<Train>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Train)

    @Update
    suspend fun update(item: Train)

    @Delete
    suspend fun delete(item: Train)
}