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

    // 리뷰조회
    @GET("/api/v1/reviews")
    fun getReviewList(
        @Header("Authorization") accessToken: String?,
    ): Call<ReviewListResponse>
}