package com.example.virginmoneyapp.data.remote

import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel
import retrofit2.http.GET

interface ApiRequest {
    @GET(ApiDetails.PEOPLES_ENDPOINT)
    suspend fun getPeoples() : ArrayList<PeopleModelItemModel>
    @GET(ApiDetails.ROOMS_ENDPOINT)
    suspend fun getRooms() : ArrayList<RoomsModelItemModel>
}