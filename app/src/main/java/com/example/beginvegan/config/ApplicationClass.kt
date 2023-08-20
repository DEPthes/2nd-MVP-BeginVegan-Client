package com.example.beginvegan.config

import android.app.Application
import android.content.SharedPreferences
import com.example.beginvegan.src.data.model.auth.Auth
import com.example.beginvegan.util.Constants.ACCESS_TOKEN
import com.example.beginvegan.util.Constants.BASE_URL
import com.example.beginvegan.util.Constants.PROVIDER_ID
import com.example.beginvegan.util.Constants.REFRESH_TOKEN
import com.example.beginvegan.util.Constants.USER_EMAIL
import com.example.beginvegan.util.Constants.USER_IMG_URL
import com.example.beginvegan.util.Constants.USER_NAME
import com.kakao.sdk.common.KakaoSdk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "fb5550ba69a57eb73aae78bc996d8a8e")
        sSharedPreferences =
            applicationContext.getSharedPreferences("BeginVegan", MODE_PRIVATE)
        initRetrofitInstance()
    }
    companion object {
        lateinit var sRetrofit: Retrofit
        lateinit var sSharedPreferences: SharedPreferences

        var xAccessToken = ACCESS_TOKEN
        var xRefreshToken = REFRESH_TOKEN
        var xAuth = Auth(PROVIDER_ID, USER_EMAIL, USER_NAME, USER_IMG_URL)
    }
    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(AccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()
        sRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}