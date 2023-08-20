package com.example.beginvegan.src.data.model.recipe

import com.google.gson.annotations.SerializedName

data class Block(
    @SerializedName("content") val content: String,
    @SerializedName("sequence") val sequence: Int
)
