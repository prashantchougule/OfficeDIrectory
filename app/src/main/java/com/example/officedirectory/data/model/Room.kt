package com.example.officedirectory.data.model

import com.google.gson.annotations.SerializedName

data class Room(
@SerializedName("id")
val id: String,

@SerializedName("maxOccupancy")
val maxOccupancy: String,

@SerializedName("createdAt")
val createdTime: String,

@SerializedName("isOccupied")
val isOccupied:Boolean = false
)
