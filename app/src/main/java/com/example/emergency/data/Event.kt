package com.example.emergency.data


data class Event(
    val id: String = "",
    val title: String = "",
    val description: String,
    val date: String,
    val photoPath: String
)