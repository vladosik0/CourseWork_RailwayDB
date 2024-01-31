package com.example.coursework.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.coursework.CourseWorkViewModel
import com.example.coursework.RailwayApplication
import com.example.coursework.ui.route_station.viewModels.*
import com.example.coursework.ui.station.viewModels.*
import com.example.coursework.ui.train_route.viewModels.*
import com.example.coursework.ui.seat.viewModels.*
import com.example.coursework.ui.train.viewModels.*
import com.example.coursework.ui.wagon.viewModels.*
import com.example.coursework.ui.ticket.viewModels.*


object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for StationEditViewModel
        initializer {
            StationEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.stationsRepository
            )
        }
        // Initializer for TicketEditViewModel
        initializer {
            TicketEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.ticketsRepository,
                railwayApplication().container.seatWithTicketsRepository,
                railwayApplication().container.startRouteStationWithTicketsRepository,
                railwayApplication().container.endRouteStationWithTicketsRepository
            )
        }
        // Initializer for RouteStationEditViewModel
        initializer {
            RouteStationEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.routeWithRouteStationsRepository,
                railwayApplication().container.routeStationsRepository,
                railwayApplication().container.stationWithRouteStationsRepository
            )
        }
        // Initializer for TrainRouteEditViewModel
        initializer {
            TrainEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.trainsRepository,
                railwayApplication().container.routeWithTrainsRepository
            )
        }
        // Initializer for SeatEditViewModel
        initializer {
            SeatEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.seatsRepository,
                railwayApplication().container.wagonWithSeatsRepository
            )
        }
        // Initializer for WagonEditViewModel
        initializer {
            WagonEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.wagonsRepository,
                railwayApplication().container.trainWithWagonsRepository
            )
        }
        // Initializer for TrainEditViewModel
        initializer {
            TrainRouteEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.trainRoutesRepository
            )
        }

        // Initializer for StationInputViewModel
        initializer {
            StationInputViewModel(railwayApplication().container.stationsRepository)
        }
        // Initializer for TicketInputViewModel
        initializer {
            TicketInputViewModel(
                railwayApplication().container.ticketsRepository,
                railwayApplication().container.seatWithTicketsRepository,
                railwayApplication().container.startRouteStationWithTicketsRepository,
                railwayApplication().container.endRouteStationWithTicketsRepository
            )
        }
        // Initializer for RouteStationInputViewModel
        initializer {
            RouteStationInputViewModel(
                railwayApplication().container.routeWithRouteStationsRepository,
                railwayApplication().container.routeStationsRepository,
                railwayApplication().container.stationWithRouteStationsRepository,
            )
        }
        // Initializer for TrainRouteInputViewModel
        initializer {
            TrainRouteInputViewModel(railwayApplication().container.trainRoutesRepository)
        }
        // Initializer for SeatInputViewModel
        initializer {
            SeatInputViewModel(
                railwayApplication().container.seatsRepository,
                railwayApplication().container.wagonWithSeatsRepository
            )
        }
        // Initializer for WagonInputViewModel
        initializer {
            WagonInputViewModel(
                railwayApplication().container.wagonsRepository,
                railwayApplication().container.trainWithWagonsRepository
            )
        }
        // Initializer for TrainInputViewModel
        initializer {
            TrainInputViewModel(
                railwayApplication().container.trainsRepository,
                railwayApplication().container.routeWithTrainsRepository
            )
        }

        // Initializer for StationDetailsViewModel
        initializer {
            StationDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.stationsRepository,
                railwayApplication().container.stationWithRouteStationsRepository
            )
        }
        // Initializer for TicketDetailsViewModel
        initializer {
            TicketDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.ticketsRepository
            )
        }
        // Initializer for RouteStationDetailsViewModel
        initializer {
            RouteStationDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.routeStationsRepository,
                railwayApplication().container.startRouteStationWithTicketsRepository,
                railwayApplication().container.endRouteStationWithTicketsRepository
            )
        }
        // Initializer for TrainRouteDetailsViewModel
        initializer {
            TrainRouteDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.trainRoutesRepository,
                railwayApplication().container.routeWithTrainsRepository,
                railwayApplication().container.routeWithRouteStationsRepository
            )
        }
        // Initializer for SeatDetailsViewModel
        initializer {
            SeatDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.seatsRepository,
                railwayApplication().container.seatWithTicketsRepository
            )
        }
        // Initializer for WagonDetailsViewModel
        initializer {
            WagonDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.wagonsRepository,
                railwayApplication().container.wagonWithSeatsRepository
            )
        }
        // Initializer for TrainDetailsViewModel
        initializer {
            TrainDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.trainsRepository,
                railwayApplication().container.trainWithWagonsRepository
            )
        }

        // Initializer for CourseWorkViewModel
        initializer {
            CourseWorkViewModel(
                railwayApplication().databaseTableNameRepository
            )
        }

        // Initializer for StationHomeViewModel
        initializer {
            StationHomeViewModel(railwayApplication().container.stationsRepository)
        }
        // Initializer for TicketHomeViewModel
        initializer {
            TicketHomeViewModel(railwayApplication().container.ticketsRepository)
        }
        // Initializer for RouteStationHomeViewModel
        initializer {
            RouteStationHomeViewModel(railwayApplication().container.routeStationsRepository)
        }
        // Initializer for TrainRouteHomeViewModel
        initializer {
            TrainRouteHomeViewModel(railwayApplication().container.trainRoutesRepository)
        }
        // Initializer for SeatHomeViewModel
        initializer {
            SeatHomeViewModel(railwayApplication().container.seatsRepository)
        }
        // Initializer for WagonHomeViewModel
        initializer {
            WagonHomeViewModel(railwayApplication().container.wagonsRepository)
        }
        // Initializer for TrainHomeViewModel
        initializer {
            TrainHomeViewModel(railwayApplication().container.trainsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [RailwayApplication].
 */
fun CreationExtras.railwayApplication(): RailwayApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RailwayApplication)