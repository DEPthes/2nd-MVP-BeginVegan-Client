package com.example.beginvegan.src.data.model.magazine

import com.google.gson.annotations.SerializedName

open class Magazine(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val title: String = "string",
    @SerializedName("editor") val editor: String = "string"
)
