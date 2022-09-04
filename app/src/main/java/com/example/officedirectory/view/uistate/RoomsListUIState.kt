package com.example.officedirectory.view.uistate

sealed class RoomsListUIState {
    object Loading : RoomsListUIState()
    data class Content(val roomsList: List<RoomItemUIState>) : RoomsListUIState()
    data class Error(val message: String) : RoomsListUIState()
}