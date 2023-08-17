package com.example.beginvegan.src.data.model.recipe

interface RecipeInterface {
    fun onGetRecipeListSuccess(response:List<RecipeListResponse>)
    fun onGetRecipeListFailure(message: String)

    fun onGetThreeRecipeListSuccess(response:List<RecipeThreeResponse>)
    fun onGetThreeRecipeListFailure(message: String)

    fun onPostRecipeDetailSuccess(response:RecipeDetailResponse)
    fun onPostRecipeDetailFailure(message: String)
}