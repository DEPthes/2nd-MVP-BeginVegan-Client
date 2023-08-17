package com.example.beginvegan.src.data.model.review

import com.google.gson.annotations.SerializedName

data class ReviewListResponse(
    @SerializedName("reviews") val reviews: List<Review>
)
