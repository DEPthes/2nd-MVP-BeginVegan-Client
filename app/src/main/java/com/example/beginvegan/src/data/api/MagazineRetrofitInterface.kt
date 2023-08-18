package com.example.beginvegan.src.data.api

import com.example.beginvegan.src.data.model.magazine.MagazineTwoResponse
import com.example.beginvegan.src.data.model.magazine.MagazineDetailResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MagazineRetrofitInterface {

    // 2가지 매거진 목록 조회 -- 2가지 매거진 조회 반환 Response 확인 필요
    @GET("/api/v1/magazines/random-megazine-list")
    fun getMagazineTwoList(
        @Header("Authorization") accessToken: String?,
    ): Call<MagazineTwoResponse>

    // 매거진 상세 정보 조회
    @POST("/api/v1/magazines/magazine-detail")
    fun postMagazineDetail(
        @Header("Authorization") accessToken: String?,
        @Body id: Int
    ): Call<MagazineDetailResponse>
}