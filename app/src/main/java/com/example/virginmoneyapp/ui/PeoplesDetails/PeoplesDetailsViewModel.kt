package com.example.virginmoneyapp.ui.PeoplesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeoplesDetailsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _peoplesDetails : MutableLiveData<PeopleModelItemModel> = MutableLiveData()

    val peoplesDetails: LiveData<PeopleModelItemModel> = _peoplesDetails

}