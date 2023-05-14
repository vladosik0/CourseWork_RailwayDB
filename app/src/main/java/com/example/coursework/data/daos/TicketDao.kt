package com.example.coursework.data.daos

import androidx.room.*
import com.example.coursework.data.classes.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Query("SELECT * from ticket")
    fun getAllItems(): Flow<List<Ticket>>

    @Query("SELECT * from ticket WHERE id = :id")
    fun getItem(id: Int): Flow<Ticket>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Ticket)

    @Update
    suspend fun update(item: Ticket)

    @Delete
    suspend fun delete(item: Ticket)
}