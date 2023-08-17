package com.example.beginvegan.src.data.model.food

import com.google.gson.annotations.SerializedName

data class Block(
    @SerializedName("content") val content: String,
    @SerializedName("sequence") val sequence: Int,
    //  blockType: Image???
    @SerializedName("blockType") val blockType: String
)
