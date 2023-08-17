package com.example.beginvegan.src.data.model.magazines

import com.google.gson.annotations.SerializedName

data class Magazine(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("editor") val editor: String,
    // ENUM TYPE?
    @SerializedName("magazineType") val magazineType: String,
)
