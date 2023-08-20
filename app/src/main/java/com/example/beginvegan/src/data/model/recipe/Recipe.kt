package com.example.beginvegan.src.data.model.recipe

import com.google.gson.annotations.SerializedName

open class Recipe(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = "name",
    @SerializedName("veganType") var veganType: String = "Vegan"
)
