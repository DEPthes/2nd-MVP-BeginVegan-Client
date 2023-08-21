package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.config.BaseResponse
import com.example.beginvegan.src.data.model.restaurant.Address
import com.example.beginvegan.src.data.model.restaurant.Restaurant
import com.example.beginvegan.src.data.model.restaurant.RestaurantDetail
import com.google.gson.annotations.SerializedName

data class UserScrapResponse(
    @SerializedName("information") val information: Scrap
):BaseResponse()

data class Scrap(
    @SerializedName("restaurants") val restaurant: List<Restaurant>
)
