package com.example.officedirectory.data.repository

import com.example.officedirectory.data.model.Person
import com.example.officedirectory.data.model.Room
import com.example.officedirectory.data.service.DirectoryService
import javax.inject.Inject

class DirectoryRepositoryImpl @Inject constructor(val api: DirectoryService):DirectoryRepository {
    override suspend fun getPeopleList(): List<Person> {
        return api.getPeopleList()
    }

    override suspend fun getRoomsList(): List<Room> {
        return api.getRoomsList()
    }
}