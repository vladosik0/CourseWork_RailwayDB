package com.example.coursework.data.daos

import androidx.room.*
import com.example.coursework.data.classes.Seat
import kotlinx.coroutines.flow.Flow

@Dao
interface SeatDao {
    @Query("SELECT * from seat")
    fun getAllItems(): Flow<List<Seat>>

    @Query("SELECT * from seat WHERE id = :id")
    fun getItem(id: Int): Flow<Seat>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Seat)

    @Update
    suspend fun update(item: Seat)

    @Delete
    suspend fun delete(item: Seat)
}