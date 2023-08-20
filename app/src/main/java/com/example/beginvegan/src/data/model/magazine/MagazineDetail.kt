package com.example.beginvegan.src.data.model.magazine

import com.google.gson.annotations.SerializedName

data class MagazineDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("editor") val editor: String,
    @SerializedName("source") val source: String,
    @SerializedName("magazineType") val magazineType: String,
    @SerializedName("magazineContents") val magazineContents: List<MagazineContents>
)
