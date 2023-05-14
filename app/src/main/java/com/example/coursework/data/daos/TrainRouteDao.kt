package com.example.coursework.data.daos

import androidx.room.*
import com.example.coursework.data.classes.TrainRoute
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainRouteDao {
    @Query("SELECT * from train_route")
    fun getAllItems(): Flow<List<TrainRoute>>

    @Query("SELECT * from train_route WHERE id = :id")
    fun getItem(id: Int): Flow<TrainRoute>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: TrainRoute)

    @Update
    suspend fun update(item: TrainRoute)

    @Delete
    suspend fun delete(item: TrainRoute)
}