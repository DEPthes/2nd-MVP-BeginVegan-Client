package com.example.beginvegan.src.data.model.restaurant

import com.example.beginvegan.config.BaseResponse
import com.example.beginvegan.src.data.model.Message
import com.google.gson.annotations.SerializedName

data class RestaurantScrapDeleteResponse(
    @SerializedName("information") val information: Message
): BaseResponse()
