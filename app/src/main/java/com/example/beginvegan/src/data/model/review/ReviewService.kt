package com.example.beginvegan.src.data.model.review

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.ReviewRetrofitInterface
import com.example.beginvegan.util.Constants
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewService(val reviewInterface: ReviewInterface) {
    private val reviewRetrofitInterface: ReviewRetrofitInterface =
        ApplicationClass.sRetrofit.create(ReviewRetrofitInterface::class.java)
    fun tryPostWriteReview(restaurantId: Int, content: String) {
        reviewRetrofitInterface.postWriteReview(ApplicationClass.xAccessToken,restaurantId, content)
            .enqueue(object : Callback<WriteReviewResponse> {
                override fun onResponse(
                    call: Call<WriteReviewResponse>,
                    response: Response<WriteReviewResponse>
                ) {
                    if (response.code() == 200) {
                        reviewInterface.onPostWriteReviewSuccess(response.body() as WriteReviewResponse)
                    } else {
                        try {
                            val gson = Gson()
                            val errorResponse =
                                gson.fromJson(
                                    response.errorBody()?.string(),
                                    ErrorResponse::class.java
                                )
                            reviewInterface.onPostWriteReviewFailure(errorResponse.message)
                        } catch (e: Exception) {
                            reviewInterface.onPostWriteReviewFailure(e.message ?: "통신 오류")
                        }
                    }
                }

                override fun onFailure(call: Call<WriteReviewResponse>, t: Throwable) {
                    reviewInterface.onPostWriteReviewFailure(t.message ?: "통신 오류")
                }

            })
    }

    fun tryGetReviewList(restaurantId: Int) {
        reviewRetrofitInterface.getReviewList(ApplicationClass.xAccessToken,restaurantId).enqueue(object : Callback<ReviewListResponse> {
            override fun onResponse(
                call: Call<ReviewListResponse>,
                response: Response<ReviewListResponse>
            ) {
                if (response.code() == 200) {
                    reviewInterface.onGetReviewListSuccess(response.body() as ReviewListResponse)
                } else {
                    try {
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        reviewInterface.onGetReviewListFailure(errorResponse.message)
                    } catch (e: Exception) {
                        reviewInterface.onGetReviewListFailure(e.message ?: "통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<ReviewListResponse>, t: Throwable) {
                reviewInterface.onGetReviewListFailure(t.message ?: "통신 오류")
            }

        })
    }
}