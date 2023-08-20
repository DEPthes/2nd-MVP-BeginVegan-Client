package com.example.beginvegan.src.data.model.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("imgageUrl") val imageUrl: String?,
    @SerializedName("marketingConsent") val marketingConsent: String?,
    @SerializedName("veganType") val veganType: String?,
    @SerializedName("provider") val provider: String,
    @SerializedName("role") val role: String?,
    @SerializedName("providerId") val providerId: String?
)
