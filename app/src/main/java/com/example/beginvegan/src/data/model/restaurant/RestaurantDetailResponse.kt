package com.example.beginvegan.src.data.model.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantDetailResponse(
    @SerializedName("restaurant")
    val restaurant: Restaurant,
    @SerializedName("menus")
    val menus: Menus
)
