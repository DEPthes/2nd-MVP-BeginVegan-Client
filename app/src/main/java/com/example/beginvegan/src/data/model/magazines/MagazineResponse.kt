package com.example.beginvegan.src.data.model.magazines

import com.google.gson.annotations.SerializedName

data class MagazineResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("editor") val editor: String,
    @SerializedName("source") val source: String,
    // ENUM TYPE?
    @SerializedName("magazineType") val magazineType: String,
    @SerializedName("magazineContents") val magazineContents: List<MagazineContents>
)
