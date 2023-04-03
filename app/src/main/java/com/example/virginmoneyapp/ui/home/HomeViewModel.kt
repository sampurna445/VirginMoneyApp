package com.example.virginmoneyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.virginmoneyapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to Virgin Money "
    }
    val text: LiveData<String> = _text
}