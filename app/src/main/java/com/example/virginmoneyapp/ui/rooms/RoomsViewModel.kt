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
import com.example.virginmoneyapp.data.models.errorHandling.ResponseOfAPI
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import retrofit2.HttpException
import java.io.IOException

@HiltViewModel
class RoomsViewModel @Inject constructor(
  val  reopository:Repository
) : ViewModel() {
    val rooms = MutableLiveData< ResponseOfAPI< ArrayList<RoomsModelItemModel>>>()

fun getRooms(){
    viewModelScope.launch {
        try {
            val res =reopository.getRooms()
            rooms.postValue(ResponseOfAPI.Success(res))
        }catch (ioe: IOException) {
            rooms.postValue(ResponseOfAPI.Failure("[IO] Error!Please Retry",ioe))

        }catch (he: HttpException){
            rooms.postValue(ResponseOfAPI.Failure("[HTTP] Error!Please Retry",he))
        }

    }
}

}