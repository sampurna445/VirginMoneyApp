package com.example.virginmoneyapp.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val peoples = MutableLiveData< ArrayList<PeopleModelItemModel>>()

    fun getPeoples() {
        viewModelScope.launch {
            val repository = repository.getPeoples()
            peoples.postValue(repository)
        }
    }
}