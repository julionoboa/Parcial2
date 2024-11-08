package com.example.emergency

import android.app.Application
import com.example.emergency.data.EventRepository

class MyApp : Application() {
    lateinit var eventRepository: EventRepository
        private set

    override fun onCreate() {
        super.onCreate()
        eventRepository = EventRepository(applicationContext)
    }
}