package com.example.coursework.data.repositories.relationsRepositories

import com.example.coursework.data.classes.relations.SeatWithTickets

interface SeatWithTicketsRepository {
    fun getSeatsAndTickets(): List<SeatWithTickets>
}