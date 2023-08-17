package com.example.beginvegan.src.data.model.recipe

import com.google.gson.annotations.SerializedName

data class Ingredients(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
