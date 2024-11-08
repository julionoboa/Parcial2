package com.example.emergency.navigation

//Julio Noboa 2022-1015

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emergency.MainScreen
import com.example.emergency.data.EventRepository
import com.example.emergency.screens.AddEventScreen
import com.example.emergency.screens.ViewEventsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(eventRepository: EventRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen"){
            MainScreen(navController)
        }
        composable("add_event") {
            AddEventScreen(navController = navController, eventRepository = eventRepository)
        }
        composable("view_events") {
            ViewEventsScreen(navController = navController, eventRepository = eventRepository)
        }
    }
}