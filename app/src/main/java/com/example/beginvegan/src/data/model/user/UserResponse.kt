package com.example.beginvegan.src.data.model.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("tokenType") val tokenType: String
)
