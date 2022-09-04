package com.example.officedirectory.view.uistate

sealed class ContactListUIState {
    object Loading : ContactListUIState()
    data class Content(val contactList: List<ContactItemUIState>) : ContactListUIState()
    data class Error(val message: String) : ContactListUIState()
}