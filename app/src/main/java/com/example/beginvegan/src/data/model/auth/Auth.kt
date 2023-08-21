package com.example.beginvegan.src.data.model.auth

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("id") val id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("email") val email: String,
    @SerializedName("imageUrl") var imageUrl: String?,
    @SerializedName("marketingConsent") var marketingConsent: String?,
    @SerializedName("veganType") var veganType: String?,
    @SerializedName("provider") val provider: String,
    @SerializedName("role") val role: String?,
    @SerializedName("providerId") val providerId: String?
)
