package com.example.coursework.data

import android.content.Context
import com.example.coursework.data.repositories.*
import com.example.coursework.data.repositories.offlineRepositories.*
import com.example.coursework.data.repositories.relationsRepositories.RouteWithRouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.RouteWithTrainsRepository
import com.example.coursework.data.repositories.relationsRepositories.SeatWithTicketsRepository
import com.example.coursework.data.repositories.relationsRepositories.StartRouteStationWithTicketsRepository
import com.example.coursework.data.repositories.relationsRepositories.StationWithRouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.TrainWithWagonsRepository
import com.example.coursework.data.repositories.relationsRepositories.WagonWithSeatsRepository
import com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories.OfflineRouteWithRouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories.OfflineRouteWithTrainsRepository
import com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories.OfflineSeatWithTicketsRepository
import com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories.OfflineStartRouteStationWithTicketsRepository
import com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories.OfflineStationWithRouteStationsRepository
import com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories.OfflineTrainWithWagonsRepository
import com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories.OfflineWagonWithSeatsRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val routeStationsRepository: RouteStationsRepository
    val seatsRepository: SeatsRepository
    val stationsRepository: StationsRepository
    val ticketsRepository: TicketsRepository
    val trainRoutesRepository: TrainRoutesRepository
    val trainsRepository: TrainsRepository
    val wagonsRepository: WagonsRepository
    val routeWithRouteStationsRepository: RouteWithRouteStationsRepository
    val stationWithRouteStationsRepository: StationWithRouteStationsRepository
    val routeWithTrainsRepository: RouteWithTrainsRepository
    val wagonWithSeatsRepository: WagonWithSeatsRepository
    val trainWithWagonsRepository: TrainWithWagonsRepository
    val seatWithTicketsRepository: SeatWithTicketsRepository
    val startRouteStationWithTicketsRepository: StartRouteStationWithTicketsRepository
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
    override val stationsRepository: StationsRepository by lazy {
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
    override val wagonsRepository: WagonsRepository by lazy {
        OfflineWagonsRepository(RailwayDatabase.getDatabase(context).wagonDao())
    }

    override val routeWithRouteStationsRepository: RouteWithRouteStationsRepository by lazy {
        OfflineRouteWithRouteStationsRepository(
            RailwayDatabase.getDatabase(context).routeWithRouteStationsDao()
        )
    }

    override val stationWithRouteStationsRepository: StationWithRouteStationsRepository by lazy {
        OfflineStationWithRouteStationsRepository(
            RailwayDatabase.getDatabase(context).stationWithRouteStationsDao()
        )
    }

    override val routeWithTrainsRepository: RouteWithTrainsRepository by lazy {
        OfflineRouteWithTrainsRepository(
            RailwayDatabase.getDatabase(context).routeWithTrainsDao()
        )
    }

    override val wagonWithSeatsRepository: WagonWithSeatsRepository by lazy {
        OfflineWagonWithSeatsRepository(
            RailwayDatabase.getDatabase(context).wagonWithSeatsDao()
        )
    }

    override val trainWithWagonsRepository: TrainWithWagonsRepository by lazy {
        OfflineTrainWithWagonsRepository(
            RailwayDatabase.getDatabase(context).trainWithWagonsDao()
        )
    }

    override val seatWithTicketsRepository: SeatWithTicketsRepository by lazy {
        OfflineSeatWithTicketsRepository(
            RailwayDatabase.getDatabase(context).seatWithTicketsDao()
        )
    }

    override val startRouteStationWithTicketsRepository: StartRouteStationWithTicketsRepository by lazy {
        OfflineStartRouteStationWithTicketsRepository(
            RailwayDatabase.getDatabase(context).startRouteStationWithTicketsDao()
        )
    }
}