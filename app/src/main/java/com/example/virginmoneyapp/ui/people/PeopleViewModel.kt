package com.example.virginmoneyapp.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.virginmoneyapp.data.models.errorHandling.ResponseOfAPI
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val peoples = MutableLiveData< ResponseOfAPI< ArrayList<PeopleModelItemModel>>>()

    fun getPeoples() {
        viewModelScope.launch {
            try {
                val res = repository.getPeoples()
                peoples.postValue(ResponseOfAPI.Success(res))
            }catch (ioe:IOException) {
                peoples.postValue(ResponseOfAPI.Failure("[IO] Error!Please Retry",ioe))

        }catch (he:HttpException){
            peoples.postValue(ResponseOfAPI.Failure("[HTTP] Error!Please Retry",he))
        }

        }
    }
}

