package com.example.coursework.data.repositories.relationsRepositories.offlineRelationsRepositories

import com.example.coursework.data.classes.relations.WagonWithSeats
import com.example.coursework.data.daos.relationsDaos.WagonWithSeatsDao
import com.example.coursework.data.repositories.relationsRepositories.WagonWithSeatsRepository

class OfflineWagonWithSeatsRepository(
    private val wagonWithSeatsDao: WagonWithSeatsDao
) : WagonWithSeatsRepository {
    override fun getWagonsAndSeats(): List<WagonWithSeats> = wagonWithSeatsDao.getWagonsAndSeats()
}