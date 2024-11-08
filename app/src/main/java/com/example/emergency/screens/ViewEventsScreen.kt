package com.example.emergency.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.emergency.data.EventRepository

//Julio Noboa 2022-1015
@Composable
fun ViewEventsScreen(navController: NavController, eventRepository: EventRepository) {
    val events = eventRepository.getAllEvents()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {
            Text("Registered Events", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (events.isEmpty()) {
            item {
                Text("No events registered yet.")
            }
        } else {
            items(events) { event ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Title: ${event.title}", style = MaterialTheme.typography.bodyLarge)
                    Text("Date: ${event.date}", style = MaterialTheme.typography.bodyMedium)
                    Text("Description: ${event.description}", style = MaterialTheme.typography.bodyMedium)

                    if (event.photoPath.isNotEmpty()) {
                        val painter = rememberImagePainter(event.photoPath)
                        Image(
                            painter = painter,
                            contentDescription = "Event Photo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(top = 8.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("add_event") }) {
                Text("Back to Add Event")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("main_screen") }) {
                Text("Back to Main Screen")
            }
        }
    }
}