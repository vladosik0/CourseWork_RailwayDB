package com.example.coursework.data.repositories.relationsRepositories

import com.example.coursework.data.classes.relations.WagonWithSeats

interface WagonWithSeatsRepository {
    fun getWagonsAndSeats(): List<WagonWithSeats>
}