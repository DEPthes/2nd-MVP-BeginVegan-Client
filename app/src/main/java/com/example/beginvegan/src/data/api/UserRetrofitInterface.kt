package com.example.beginvegan.src.data.api

import com.example.beginvegan.src.data.model.user.VeganType
import com.example.beginvegan.src.data.model.restaurant.RestaurantReviewResponse
import com.example.beginvegan.src.data.model.user.UserResponse
import com.example.beginvegan.src.data.model.user.VeganTypeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserRetrofitInterface {

    // AccessToken을 이용한 정보 조회
    @GET("/api/v1/users")
    fun getUser(): Call<UserResponse>

//    @PATCH("/api/v1/users")
//    fun getAlarmUser(): Call<RestaurantReviewResponse>

    // 유저 비건 타입 변경
    @POST("/api/v1/users/vegan-type")
    fun postUserVeganType(
        @Body veganType: VeganType
    ): Call<VeganTypeResponse>
}