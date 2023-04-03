package com.example.virginmoneyapp.data.repository

import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel
import com.example.virginmoneyapp.data.remote.ApiRequest
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
):Repository{
   override suspend fun getPeoples() : ArrayList<PeopleModelItemModel> = apiRequest.getPeoples()
    override suspend fun getRooms() : ArrayList<RoomsModelItemModel> = apiRequest.getRooms()
}