package com.example.beginvegan.src.data.model.recipe

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.RecipeRetrofitInterface
import com.example.beginvegan.util.Constants
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeService(val recipeInterface: RecipeInterface) {
    private val recipeRetrofitInterface: RecipeRetrofitInterface =
        ApplicationClass.sRetrofit.create(RecipeRetrofitInterface::class.java)
    private val accessToken = ("Bearer "+(ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        null
    )))
    // 전체 레시피 목록 조회
    fun tryGetRecipeList() {
        recipeRetrofitInterface.getRecipeList(accessToken)
            .enqueue(object : Callback<List<RecipeListResponse>> {
                override fun onResponse(
                    call: Call<List<RecipeListResponse>>,
                    response: Response<List<RecipeListResponse>>
                ) {
                    if (response.code() == 200) {
                        recipeInterface.onGetRecipeListSuccess(response.body() as List<RecipeListResponse>)
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

                override fun onFailure(call: Call<List<RecipeListResponse>>, t: Throwable) {
                    recipeInterface.onGetRecipeListFailure(t.message ?: "통신 오류")
                }

            })
    }

    // 3가지 음식 목록 조회
    fun tryGetThreeRecipeList() {
        recipeRetrofitInterface.getThreeRecipeList(accessToken)
            .enqueue(object : Callback<List<RecipeThreeResponse>> {
                override fun onResponse(
                    call: Call<List<RecipeThreeResponse>>,
                    response: Response<List<RecipeThreeResponse>>
                ) {
                    if (response.code() == 200) {
                        recipeInterface.onGetThreeRecipeListSuccess(response.body() as List<RecipeThreeResponse>)
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

                override fun onFailure(call: Call<List<RecipeThreeResponse>>, t: Throwable) {
                    recipeInterface.onGetThreeRecipeListFailure(t.message ?: "통신 오류")
                }

            })

    }

    // 레시피 상세 정보 조회
    fun tryPostRecipeDetail(recipeId: Int) {
        recipeRetrofitInterface.postRecipeDetail(accessToken,recipeId)
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