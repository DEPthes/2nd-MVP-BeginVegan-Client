package com.example.beginvegan.src.data.model.auth

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("providerId") val providerId: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("email") val email: String,
    @SerializedName("profileImgUrl") val profileImgUrl: String
)
