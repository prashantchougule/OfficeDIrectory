package com.example.officedirectory.domain

import com.example.officedirectory.data.model.Person
import com.example.officedirectory.data.repository.DirectoryRepository
import com.example.officedirectory.view.uistate.ContactItemUIState
import java.lang.reflect.Constructor
import javax.inject.Inject

class GetContactUseCase @Inject constructor( private val repository: DirectoryRepository){
    suspend fun invoke():List<Person>{
        return repository.getPeopleList()
    }
}