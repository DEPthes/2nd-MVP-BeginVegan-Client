package com.example.beginvegan.src.data.model.recipe

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.RecipeRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class RecipeService(val recipeInterface: RecipeInterface) {
    private val recipeRetrofitInterface: RecipeRetrofitInterface =
        ApplicationClass.sRetrofit.create(RecipeRetrofitInterface::class.java)
    // 전체 레시피 목록 조회
    fun tryGetRecipeList() {
        recipeRetrofitInterface.getRecipeList(ApplicationClass.xAccessToken)
            .enqueue(object : Callback<RecipeListResponse> {
                override fun onResponse(
                    call: Call<RecipeListResponse>,
                    response: Response<RecipeListResponse>
                ) {
                    if (response.code() == 200) {
                        recipeInterface.onGetRecipeListSuccess(response.body() as RecipeListResponse)
                    } else {
                        try {
                            val gson = Gson()
                            val errorResponse =
                                gson.fromJson(
                                    response.errorBody()?.string(),
                                    ErrorResponse::class.java
                                )
                            recipeInterface.onGetRecipeListFailure(errorResponse.message)
                        } catch (e: Exception) {
                            recipeInterface.onGetRecipeListFailure(e.message ?: "통신 오류")
                        }
                    }

                }

                override fun onFailure(call: Call<RecipeListResponse>, t: Throwable) {
                    recipeInterface.onGetRecipeListFailure(t.message ?: "통신 오류")
                }

            })
    }

    // 3가지 음식 목록 조회
    fun tryGetThreeRecipeList() {
        recipeRetrofitInterface.getThreeRecipeList(ApplicationClass.xAccessToken)
            .enqueue(object : Callback<RecipeThreeResponse> {
                override fun onResponse(
                    call: Call<RecipeThreeResponse>,
                    response: Response<RecipeThreeResponse>
                ) {
                    if (response.code() == 200) {
                        recipeInterface.onGetThreeRecipeListSuccess(response.body() as RecipeThreeResponse)
                    } else {
                        try {
                            val gson = Gson()
                            val errorResponse =
                                gson.fromJson(
                                    response.errorBody()?.string(),
                                    ErrorResponse::class.java
                                )
                            recipeInterface.onGetThreeRecipeListFailure(errorResponse.message)
                        } catch (e: Exception) {
                            recipeInterface.onGetThreeRecipeListFailure(e.message ?: "통신 오류")
                        }
                    }
                }

                override fun onFailure(call: Call<RecipeThreeResponse>, t: Throwable) {
                    recipeInterface.onGetThreeRecipeListFailure(t.message ?: "통신 오류")
                }

            })

    }

    // 레시피 상세 정보 조회
    fun tryPostRecipeDetail(recipeId: Int) {
        recipeRetrofitInterface.postRecipeDetail(ApplicationClass.xAccessToken,recipeId)
            .enqueue(object : Callback<RecipeDetailResponse> {
                override fun onResponse(
                    call: Call<RecipeDetailResponse>,
                    response: Response<RecipeDetailResponse>
                ) {
                    if (response.code() == 200) {
                        recipeInterface.onPostRecipeDetailSuccess(response.body() as RecipeDetailResponse)
                    } else {
                        try {
                            val gson = Gson()
                            val errorResponse =
                                gson.fromJson(
                                    response.errorBody()?.string(),
                                    ErrorResponse::class.java
                                )
                            recipeInterface.onPostRecipeDetailFailure(errorResponse.message)
                        } catch (e: Exception) {
                            recipeInterface.onPostRecipeDetailFailure(e.message ?: "통신 오류")
                        }
                    }
                }

                override fun onFailure(call: Call<RecipeDetailResponse>, t: Throwable) {
                    recipeInterface.onPostRecipeDetailFailure(t.message ?: "통신 오류")
                }

            })

    }
}