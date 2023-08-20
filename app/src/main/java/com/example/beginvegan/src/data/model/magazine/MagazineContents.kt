package com.example.beginvegan.src.data.model.magazine

import com.google.gson.annotations.SerializedName

data class MagazineContents(
    @SerializedName("content") val content: String,
    @SerializedName("sequence") val sequence: Int
)
