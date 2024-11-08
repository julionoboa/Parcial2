package com.example.emergency

//Julio Noboa 2022-1015

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emergency.data.EventRepository
import com.example.emergency.navigation.AppNavigation
import com.example.emergency.ui.theme.EmergencyTheme

class MainActivity : ComponentActivity() {
    private lateinit var eventRepository: EventRepository

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventRepository = (applicationContext as MyApp).eventRepository

        setContent {
            EmergencyTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(eventRepository = eventRepository)
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {

    Scaffold(
        topBar = {
            Text(
                text = "Emergency App",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    navController.navigate("add_event")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = "Create Event",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate("view_events")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = "View Events",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White)
                )
            }
        }
    }
}