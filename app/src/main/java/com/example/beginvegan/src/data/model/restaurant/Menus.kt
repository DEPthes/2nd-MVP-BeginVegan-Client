package com.example.beginvegan.src.data.model.restaurant

import com.google.gson.annotations.SerializedName

data class Menus(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageUrl") val imageUrl: String
)