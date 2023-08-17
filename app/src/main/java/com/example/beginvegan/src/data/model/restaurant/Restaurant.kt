package com.example.beginvegan.src.data.model.restaurant

import com.google.gson.annotations.SerializedName


open class Restaurant(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("businessHours") val businessHours: String,
    @SerializedName("contactNumber") val contactNumber: String,
    @SerializedName("address") val address: Address,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("kakaoMapUrl") val kakaoMapUrl: String,
    @SerializedName("imageUrl") val imageUrl:String,
    @SerializedName("imageSource") val imageSource: String
)