package com.example.beginvegan.src.data.model.recipe

interface RecipeInterface {
    // 전체 레시피 목록 조회
    fun onGetRecipeListSuccess(response:RecipeListResponse)
    fun onGetRecipeListFailure(message: String)

    // 3가지 음식 목록 조회
    fun onGetThreeRecipeListSuccess(response:RecipeThreeResponse)
    fun onGetThreeRecipeListFailure(message: String)

    // 레시피 상세 정보 조회
    fun onPostRecipeDetailSuccess(response:RecipeDetailResponse)
    fun onPostRecipeDetailFailure(message: String)
}