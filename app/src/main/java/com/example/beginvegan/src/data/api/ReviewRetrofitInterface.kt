package com.example.beginvegan.src.data.api

import com.example.beginvegan.src.data.model.review.ReviewListResponse
import com.example.beginvegan.src.data.model.review.ReviewRequest
import com.example.beginvegan.src.data.model.review.WriteReviewResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewRetrofitInterface {

    // 리뷰 등록
    @POST("/api/v1/reviews")
    fun postWriteReview(
        @Header("Authorization") accessToken: String?,
        @Body review: ReviewRequest
    ): Call<WriteReviewResponse>

    // 리뷰조회
    @GET("/api/v1/reviews")
    fun getReviewList(
        @Header("Authorization") accessToken: String?,
        @Query("page") page: Int
    ): Call<ReviewListResponse>
}