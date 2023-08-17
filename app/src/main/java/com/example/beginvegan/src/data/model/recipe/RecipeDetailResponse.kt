package com.example.beginvegan.src.data.model.recipe

import com.google.gson.annotations.SerializedName


// 레시피 상세 정보 조회
data class RecipeDetailResponse(
    @SerializedName("ingredients") val ingredients: List<Ingredients>,
    @SerializedName("blocks") val blocks: List<Block>
):Recipe()
