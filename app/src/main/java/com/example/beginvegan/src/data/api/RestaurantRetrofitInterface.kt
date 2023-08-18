package com.example.beginvegan.src.data.api

import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantReviewResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantRetrofitInterface {

    // 식당/카페 상세 정보(메뉴까지) 조회
    @GET("/api/v1/restaurants/{restaurant-id}")
    fun getRestaurantsDetail(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int
    ): Call<RestaurantDetailResponse>

    // 식당/카페 리뷰 조회
    @GET("/api/v1/restaurants/review/{restaurant-id}")
    fun getRestaurantReview(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int
    ): Call<RestaurantReviewResponse>

    // 식당/카페 스크랩
    @POST("@Path(\"restaurant-id\") id: String")
    fun postScrapRestaurant(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int
    )

    @DELETE("/api/v1/restaurants/{restaurant-id}")
    fun deleteScrapRestaurant(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int
    )

}