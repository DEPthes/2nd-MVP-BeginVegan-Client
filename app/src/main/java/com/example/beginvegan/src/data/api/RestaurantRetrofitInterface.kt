package com.example.beginvegan.src.data.api

import com.example.beginvegan.src.data.model.restaurant.Coordinate
import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantReviewResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapDeleteResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantRetrofitInterface {

    // 식당/카페 상세 정보(메뉴 까지) 조회
    @GET("/api/v1/restaurants/{restaurant-id}")
    fun getRestaurantsDetail(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int
    ): Call<RestaurantDetailResponse>

    // 식당/카페 리뷰 조회
    @GET("/api/v1/restaurants/review/{restaurant-id}")
    fun getRestaurantReview(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int,
        @Query("page") page: Int
    ): Call<RestaurantReviewResponse>

    // 식당/카페 스크랩
    @POST("/api/v1/restaurants/{restaurant-id}")
    fun postScrapRestaurant(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int
    ): Call<RestaurantScrapResponse>

    // 식당/카페 스크랩 해제
    @DELETE("/api/v1/restaurants/{restaurant-id}")
    fun deleteScrapRestaurant(
        @Header("Authorization") accessToken: String?,
        @Path("restaurant-id") id: Int
    ): Call<RestaurantScrapDeleteResponse>

    // 주변 식당/카페 조회
    @POST("/api/v1/restaurants/around")
    fun postFindRestaurant(
        @Header("Authorization") accessToken: String?,
        @Body coordinate: Coordinate
    ):Call<RestaurantFindResponse>
}