package com.example.virginmoneyapp.ui.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel
import com.example.virginmoneyapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
  val  reopository:Repository
) : ViewModel() {
    val rooms = MutableLiveData<ArrayList<RoomsModelItemModel>>()

fun getRooms(){
    viewModelScope.launch {
        val repository = reopository.getRooms()
        rooms.postValue(repository)
    }
}

}