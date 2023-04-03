package com.example.virginmoneyapp.data.repository

import com.example.virginmoneyapp.data.models.peopleModel.PeopleModelItemModel
import com.example.virginmoneyapp.data.models.roomsModel.RoomsModelItemModel

interface Repository {
    suspend fun getPeoples() : ArrayList<PeopleModelItemModel>
    suspend fun getRooms() : ArrayList<RoomsModelItemModel>
}