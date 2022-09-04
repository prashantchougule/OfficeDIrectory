package com.example.officedirectory.data.repository

import com.example.officedirectory.data.model.Person
import com.example.officedirectory.data.model.Room

interface DirectoryRepository {
    suspend fun getPeopleList(): List<Person>
    suspend fun getRoomsList(): List<Room>
}