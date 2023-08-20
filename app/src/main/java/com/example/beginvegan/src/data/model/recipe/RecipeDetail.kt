package com.example.beginvegan.src.data.model.recipe

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class RecipeDetail(
    @SerializedName("source") val source: String,
    @SerializedName("ingredients") val ingredients: List<Ingredients>,
    @SerializedName("blocks") val blocks: List<Block>
): Recipe()
