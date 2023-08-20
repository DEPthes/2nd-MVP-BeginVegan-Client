package com.example.beginvegan.src.data.model.auth

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class AuthSignOutResponse(
    @SerializedName("information") val information: AuthSignOut
): BaseResponse()

data class AuthSignOut(
    @SerializedName("message") val message: String
)