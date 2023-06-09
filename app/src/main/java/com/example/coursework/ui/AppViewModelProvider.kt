package com.example.coursework.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
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
                railwayApplication().container.ticketsRepository
            )
        }
        // Initializer for RouteStationEditViewModel
        initializer {
            RouteStationEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.routeStationsRepository
            )
        }
        // Initializer for TrainRouteEditViewModel
        initializer {
            TrainEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.trainsRepository
            )
        }
        // Initializer for SeatEditViewModel
        initializer {
            SeatEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.seatsRepository
            )
        }
        // Initializer for WagonEditViewModel
        initializer {
            WagonEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.wagonsRepository
            )
        }
        // Initializer for TrainEditViewModel
        initializer {
            TrainEditViewModel(
                this.createSavedStateHandle(),
                railwayApplication().container.trainsRepository
            )
        }

        // Initializer for StationInputViewModel
        initializer {
            StationInputViewModel(railwayApplication().container.stationsRepository)
        }
        // Initializer for TicketInputViewModel
        initializer {
            TicketInputViewModel(railwayApplication().container.ticketsRepository)
        }
        // Initializer for RouteStationInputViewModel
        initializer {
            RouteStationInputViewModel(railwayApplication().container.routeStationsRepository)
        }
        // Initializer for TrainRouteInputViewModel
        initializer {
            TrainRouteInputViewModel(railwayApplication().container.trainRoutesRepository)
        }
        // Initializer for SeatInputViewModel
        initializer {
            SeatInputViewModel(railwayApplication().container.seatsRepository)
        }
        // Initializer for WagonInputViewModel
        initializer {
            WagonInputViewModel(railwayApplication().container.wagonsRepository)
        }
        // Initializer for TrainInputViewModel
        initializer {
            TrainInputViewModel(railwayApplication().container.trainsRepository)
        }

        // Initializer for StationDetailsViewModel
        initializer {
            StationDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.stationsRepository
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
                railwayApplication().container.routeStationsRepository
            )
        }
        // Initializer for TrainRouteDetailsViewModel
        initializer {
            TrainRouteDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.trainRoutesRepository
            )
        }
        // Initializer for SeatDetailsViewModel
        initializer {
            SeatDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.seatsRepository
            )
        }
        // Initializer for WagonDetailsViewModel
        initializer {
            WagonDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.wagonsRepository
            )
        }
        // Initializer for TrainDetailsViewModel
        initializer {
            TrainDetailsViewModel(
                createSavedStateHandle(),
                railwayApplication().container.trainsRepository
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