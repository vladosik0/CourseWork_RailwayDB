package com.example.coursework

import android.app.Application
import com.example.coursework.data.AppContainer
import com.example.coursework.data.AppDataContainer

class RailwayApplication:Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}