package com.example.beginvegan.src.data.model.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantReviewResponse(
    @SerializedName("review")
    val review: List<String>
)
