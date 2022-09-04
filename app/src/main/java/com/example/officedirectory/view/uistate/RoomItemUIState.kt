package com.example.officedirectory.view.uistate


data class RoomItemUIState (
    val id: String,
    val maxOccupancy: String,
    val createdTime: String,
    val isOccupied:Boolean = false
)