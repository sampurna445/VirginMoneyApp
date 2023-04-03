package com.example.virginmoneyapp.data.models.roomsModel


import com.google.gson.annotations.SerializedName

data class RoomsModelItemModel(
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("isOccupied")
    val isOccupied: Boolean? = false,
    @SerializedName("maxOccupancy")
    val maxOccupancy: Int? = 0
):java.io.Serializable