package com.example.emergency.screens

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emergency.data.Event
import com.example.emergency.data.EventRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEventScreen(navController: NavController, eventRepository: EventRepository) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            photoUri = uri
        }
    )

    val pickImage: () -> Unit = {
        pickImageLauncher.launch("image/*")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Add Event", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Title:")
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Event Title") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Description:")
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Event Description") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = false,
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Photo:")
        OutlinedTextField(
            value = photoUri?.toString() ?: "",
            onValueChange = { newValue ->
                photoUri = Uri.parse(newValue)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Event Photo") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = pickImage) {
            Text("Pick an Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = {
                val event = Event(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = description,
                    date = currentDate,
                    photoPath = photoUri?.toString() ?: ""
                )
                eventRepository.insertEvent(event)
                navController.navigate("view_events")
            }) {
                Text("Register Event")
            }
        }
    }
}