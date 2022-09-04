package com.example.officedirectory.domain

import com.example.officedirectory.data.model.Room
import com.example.officedirectory.data.repository.DirectoryRepository
import com.example.officedirectory.view.uistate.RoomItemUIState
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(private val repository: DirectoryRepository) {
    suspend fun invoke():List<Room>{
        return repository.getRoomsList()
    }
}