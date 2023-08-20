package com.example.beginvegan.src.data.model.recipe

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName


// 레시피 상세 정보 조회
data class RecipeDetailResponse(
    @SerializedName("information") val information: RecipeDetail
) : BaseResponse()
