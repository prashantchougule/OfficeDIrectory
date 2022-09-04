package com.example.officedirectory.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officedirectory.domain.GetRoomsUseCase
import com.example.officedirectory.view.uistate.RoomItemUIState
import com.example.officedirectory.view.uistate.RoomsListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RoomsListViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase
    ): ViewModel(){

    private val _viewState = MutableLiveData<RoomsListUIState>()
    val viewState: LiveData<RoomsListUIState>
        get() = _viewState

    fun fetchRooms() {
        viewModelScope.launch {
            _viewState.postValue(RoomsListUIState.Loading)
            try {
                // Data call to fetch news
                val roomsResponse = getRoomsUseCase.invoke()
                roomsResponse.let {
                    _viewState.postValue(RoomsListUIState.Content(
                        //This transformation can be done using mapper in Data layer
                        roomsResponse.map {
                            RoomItemUIState(
                                it.id,
                                it.maxOccupancy,
                                it.createdTime,
                                it.isOccupied
                            )
                        }
                    ))
                }
            }catch (ex: Exception){
                _viewState.postValue(RoomsListUIState.Error(handleExcpetion(ex)))
            }
        }
    }
    private fun handleExcpetion(ex: Exception): String{
        //We can handle all exceptions separately here
        return ex.message?: "Error occurred"
    }
}