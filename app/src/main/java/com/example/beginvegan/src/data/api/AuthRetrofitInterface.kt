package com.example.beginvegan.src.data.api

import com.example.beginvegan.src.data.model.auth.Auth
import com.example.beginvegan.src.data.model.auth.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    // 유저 회원가입
    @POST("/auth/sign-up")
    fun postAuthSignUp(
        @Body auth: Auth
    ): Call<AuthResponse>

    // 유저 로그아웃
    @POST("/auth/sign-out")
    fun postAuthSignOut(
    )

    // 토큰갱신
    @POST("/auth/refresh")
    fun postTokenRefresh(
    )
}