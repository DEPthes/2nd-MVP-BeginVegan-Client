package com.example.beginvegan.src.data.model.review

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("reviews") val review: String
)
