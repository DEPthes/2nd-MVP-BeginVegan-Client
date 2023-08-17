package com.example.beginvegan.src.data.model.recipe

import com.google.gson.annotations.SerializedName

// 전체 레시피 목록 조회
data class RecipeListResponse(
    @SerializedName("description") val description: String
):Recipe()
