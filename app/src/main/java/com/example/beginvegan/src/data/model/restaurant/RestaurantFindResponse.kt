package com.example.beginvegan.src.data.model.restaurant

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class RestaurantFindResponse(
    @SerializedName("information") val information: List<NearRestaurant>
):BaseResponse()
