package com.example.beginvegan.src.data.model.review

import com.google.gson.annotations.SerializedName

data class ReviewRequest(
    @SerializedName("restaurantId") val restaurantId: Int,
    @SerializedName("content") val content: String
)
