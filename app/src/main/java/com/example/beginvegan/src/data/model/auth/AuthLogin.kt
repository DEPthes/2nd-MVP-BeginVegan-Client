package com.example.beginvegan.src.data.model.auth

import com.google.gson.annotations.SerializedName

data class AuthLogin(
    @SerializedName("providerId") var providerId: String,
    @SerializedName("email") var email: String
)
