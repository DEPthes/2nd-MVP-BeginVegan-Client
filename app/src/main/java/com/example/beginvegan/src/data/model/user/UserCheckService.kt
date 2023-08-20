package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.UserRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserCheckService(val userCheckInterface: UserCheckInterface) {
    private val userRetrofitInterface: UserRetrofitInterface = ApplicationClass.sRetrofit.create(
        UserRetrofitInterface::class.java)

    fun tryGetUser(){
        userRetrofitInterface.getUser(ApplicationClass.xAccessToken).enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.code() == 200){
                    userCheckInterface.onGetUserSuccess(response.body() as UserResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        userCheckInterface.onGetUserFailure(errorResponse.message)
                    }catch(e:Exception){
                        userCheckInterface.onGetUserFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                userCheckInterface.onGetUserFailure(t.message?:"통신 오류")
            }

        })
    }
}