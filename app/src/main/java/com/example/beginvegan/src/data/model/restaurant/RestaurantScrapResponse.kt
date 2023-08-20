package com.example.beginvegan.src.data.model.restaurant

import android.os.Message
import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class RestaurantScrapResponse(
    @SerializedName("information") val information: Message
): BaseResponse()
