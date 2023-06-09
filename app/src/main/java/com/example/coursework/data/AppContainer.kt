package com.example.coursework.data

import android.content.Context
import com.example.coursework.data.repositories.*
import com.example.coursework.data.repositories.offlineRepositories.*

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val routeStationsRepository: RouteStationsRepository
    val seatsRepository: SeatsRepository
    val stationsRepository:StationsRepository
    val ticketsRepository: TicketsRepository
    val trainRoutesRepository: TrainRoutesRepository
    val trainsRepository: TrainsRepository
    val wagonsRepository:WagonsRepository
}

/**
 * [AppContainer] implementation that provides instance of offlineRepositories
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementations for repositories
     */
    override val routeStationsRepository: RouteStationsRepository by lazy {
        OfflineRouteStationsRepository(RailwayDatabase.getDatabase(context).routeStationDao())
    }
    override val seatsRepository: SeatsRepository by lazy {
        OfflineSeatsRepository(RailwayDatabase.getDatabase(context).seatDao())
    }
    override val stationsRepository:StationsRepository by lazy {
        OfflineStationsRepository(RailwayDatabase.getDatabase(context).stationDao())
    }
    override val ticketsRepository: TicketsRepository by lazy {
        OfflineTicketsRepository(RailwayDatabase.getDatabase(context).ticketDao())
    }
    override val trainRoutesRepository: TrainRoutesRepository by lazy {
        OfflineTrainRoutesRepository(RailwayDatabase.getDatabase(context).trainRouteDao())
    }
    override val trainsRepository: TrainsRepository by lazy {
        OfflineTrainsRepository(RailwayDatabase.getDatabase(context).trainDao())
    }
    override val wagonsRepository:WagonsRepository by lazy {
        OfflineWagonsRepository(RailwayDatabase.getDatabase(context).wagonDao())
    }
}