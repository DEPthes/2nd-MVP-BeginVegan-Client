package com.example.beginvegan.src.data.model.magazines

import com.google.gson.annotations.SerializedName

data class MagazineContents(
    @SerializedName("content") val content: String,
    @SerializedName("sequence") val sequence: Int,
    // ENUM TYPE
    @SerializedName("blockType") val blockType: String
)
