package com.example.beginvegan.src.data.model.auth

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class AuthTokenResponse(
    @SerializedName("information") val information: AuthResponse
):BaseResponse()
