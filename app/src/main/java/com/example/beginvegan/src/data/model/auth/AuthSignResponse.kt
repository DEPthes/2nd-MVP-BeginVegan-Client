package com.example.beginvegan.src.data.model.auth

import com.google.gson.annotations.SerializedName

data class AuthSignResponse(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: AuthResponse
)


