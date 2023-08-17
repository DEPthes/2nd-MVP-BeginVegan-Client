package com.example.beginvegan.src.data.model.recipe

import com.google.gson.annotations.SerializedName

open class Recipe(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "name",
    @SerializedName("veganType") val veganType: String = "Vegan"
)
