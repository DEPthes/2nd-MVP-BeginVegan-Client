package com.example.beginvegan.src.data.model.magazine

import com.google.gson.annotations.SerializedName

data class Magazine(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String ,
    @SerializedName("editor") val editor: String,
    @SerializedName("magazineType") val magazineType: String
)
