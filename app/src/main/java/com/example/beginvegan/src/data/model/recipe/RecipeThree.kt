package com.example.beginvegan.src.data.model.recipe

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeThree(
    @SerializedName("description") val description: String
): Recipe(), Serializable
