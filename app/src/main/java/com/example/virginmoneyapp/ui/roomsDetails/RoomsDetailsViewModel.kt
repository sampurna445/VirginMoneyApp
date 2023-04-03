package com.example.virginmoneyapp.ui.roomsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel
import com.example.virginmoneyapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class RoomsDetailsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _roomsDetails : MutableLiveData<RoomsModelItemModel> = MutableLiveData()

    val roomsDetails: LiveData<RoomsModelItemModel> = _roomsDetails
}