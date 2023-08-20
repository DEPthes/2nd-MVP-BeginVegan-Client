package com.example.beginvegan.src.data.model.restaurant

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("province") val province: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("roadName") val roadName: String?,
    @SerializedName("detailAddress") val detailAddress: String
)