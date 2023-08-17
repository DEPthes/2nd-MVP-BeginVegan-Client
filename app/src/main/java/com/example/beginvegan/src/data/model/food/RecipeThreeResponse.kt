package com.example.beginvegan.src.data.model.food

import com.google.gson.annotations.SerializedName

// 3가지 음식 목록 조회
data class RecipeThreeResponse(
    @SerializedName("ingredients") val ingredients: Ingredients
):Recipe()
