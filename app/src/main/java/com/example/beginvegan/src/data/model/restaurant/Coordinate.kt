package com.example.beginvegan.src.data.model.restaurant

import com.google.gson.annotations.SerializedName

data class Coordinate(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude:String
)
