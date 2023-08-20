package com.example.beginvegan.src.data.model.auth

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("providerId") var providerId: String,
    @SerializedName("nickname") var nickname: String,
    @SerializedName("email") var email: String,
    @SerializedName("profileImgUrl") var profileImgUrl: String?
)