package com.example.coursework.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coursework.data.classes.*
import com.example.coursework.data.daos.*


@Database(
    entities = [
    RouteStation::class,
    Seat::class,
    Station::class,
    Ticket::class,
    Train::class,
    TrainRoute::class,
    Wagon::class],
    version = 1,
    exportSchema = false
)
abstract class RailwayDatabase: RoomDatabase() {
    abstract fun routeStationDao():RouteStationDao
    abstract fun seatDao():SeatDao
    abstract fun stationDao():StationDao
    abstract fun ticketDao():TicketDao
    abstract fun trainDao():TrainDao
    abstract fun trainRouteDao():TrainRouteDao
    abstract fun wagonDao():WagonDao

    companion object {
        @Volatile
        private var Instance: RailwayDatabase? = null
        fun getDatabase(context: Context): RailwayDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context,RailwayDatabase::class.java,"railway_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}