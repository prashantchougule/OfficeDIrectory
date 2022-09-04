package com.example.officedirectory.data.service

import com.example.officedirectory.data.model.Person
import com.example.officedirectory.data.model.Room
import retrofit2.http.GET

interface DirectoryService {

    @GET("people")
    suspend fun getPeopleList() : List<Person>

    @GET("rooms")
    suspend fun getRoomsList() : List<Room>
}