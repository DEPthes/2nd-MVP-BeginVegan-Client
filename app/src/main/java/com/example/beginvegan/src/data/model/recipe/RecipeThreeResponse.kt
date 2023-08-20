package com.example.beginvegan.src.data.model.recipe

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

// 3가지 음식 목록 조회
data class RecipeThreeResponse(
    @SerializedName("information") val information: List<RecipeThree>
):BaseResponse()
