package com.example.beginvegan.src.data.api

import com.example.beginvegan.src.data.model.recipe.RecipeDetailResponse
import com.example.beginvegan.src.data.model.recipe.RecipeListResponse
import com.example.beginvegan.src.data.model.recipe.RecipeThreeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RecipeRetrofitInterface {

    // 전체 레시피 목록 조회
    @GET("/api/v1/foods/recipe-list")
    fun getRecipeList(
        @Header("Authorization") accessToken: String?,
    ): Call<RecipeListResponse>

    // 3가지 음식 목록 조회
    @GET("/api/v1/foods/random-food-list")
    fun getThreeRecipeList(
        @Header("Authorization") accessToken: String?,
    ):Call<RecipeThreeResponse>

    // 레시피 상세 정보 조회
    @POST("/api/v1/foods/recipe-detail")
    fun postRecipeDetail(
        @Header("Authorization") accessToken: String?,
        @Body id: Int
    ): Call<RecipeDetailResponse>
}