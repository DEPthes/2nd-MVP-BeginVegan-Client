package com.example.beginvegan.src.data.model.auth

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.AuthRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthTokenRefreshService(val authTokenRefreshInterface: AuthTokenRefreshInterface) {
    private val authRetrofitInterface: AuthRetrofitInterface = ApplicationClass.sRetrofit.create(AuthRetrofitInterface::class.java)

    fun tryPostTokenRefreshSuccess(refreshToken: String){
        authRetrofitInterface.postTokenRefresh(refreshToken).enqueue(object: Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.code() == 200){
                    authTokenRefreshInterface.onPostTokenRefreshSuccess(response.body() as AuthResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        authTokenRefreshInterface.onPostTokenRefreshFailure(errorResponse.message)
                    }catch(e:Exception){
                        authTokenRefreshInterface.onPostTokenRefreshFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                authTokenRefreshInterface.onPostTokenRefreshFailure(t.message?:"통신 오류")
            }

        })
    }
}