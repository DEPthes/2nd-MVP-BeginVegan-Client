package com.example.beginvegan.src.data.model.recipe

import com.google.gson.annotations.SerializedName

data class RecipeList(
    @SerializedName("ingredients") val ingredients: List<Ingredients>
):Recipe()
