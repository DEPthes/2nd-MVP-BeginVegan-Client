package com.example.beginvegan.src.data.model.auth

import com.google.gson.annotations.SerializedName

data class AuthLoginResponse(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: AuthResponse
)


