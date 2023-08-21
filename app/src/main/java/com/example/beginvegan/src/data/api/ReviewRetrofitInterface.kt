package com.example.beginvegan.src.data.api

import com.example.beginvegan.src.data.model.review.ReviewListResponse
import com.example.beginvegan.src.data.model.review.WriteReviewResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewRetrofitInterface {

    // 리뷰 등록
    @POST("/api/v1/reviews")
    fun postWriteReview(
        @Header("Authorization") accessToken: String?,
        @Body restaurant: Int,
        @Body content: String
    ): Call<WriteReviewResponse>

    @GET("/api/v1/restaurants/review/{restaurant-id}?page={n}")
    fun getReviewList(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int,
        @Path("n") page: Int
    ): Call<ReviewListResponse>
}