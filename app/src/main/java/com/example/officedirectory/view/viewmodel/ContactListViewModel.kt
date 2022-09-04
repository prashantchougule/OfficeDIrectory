package com.example.officedirectory.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officedirectory.domain.GetContactUseCase
import com.example.officedirectory.view.uistate.ContactItemUIState
import com.example.officedirectory.view.uistate.ContactListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val getContactUseCase: GetContactUseCase
) : ViewModel(){
    private val _viewState = MutableLiveData<ContactListUIState>()
    val viewState: LiveData<ContactListUIState>
        get() = _viewState

    fun fetchContacts() {
        viewModelScope.launch {
            _viewState.postValue(ContactListUIState.Loading)
            try {
                // Data call to fetch news
                val contactResponse = getContactUseCase.invoke()
                    _viewState.postValue(
                        ContactListUIState.Content(
                        //This transformation can be done using mapper in Data layer
                            contactResponse.map {
                            ContactItemUIState(
                                it.id,
                                it.createdDate,
                                it.avtarUrl,
                                it.jobTitle,
                                it.favouriteColor,
                                it.emailId,
                                it.firstname,
                                it.lastname
                            )
                        }
                    ))
            }catch (ex: Exception){
                _viewState.postValue(ContactListUIState.Error(handleExcpetion(ex)))
            }
        }
    }
    private fun handleExcpetion(ex: Exception): String{
        //We can handle all exceptions separately here
        return ex.message?: "Error occurred"
    }
}