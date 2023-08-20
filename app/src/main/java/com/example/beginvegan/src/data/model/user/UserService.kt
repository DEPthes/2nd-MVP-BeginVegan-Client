package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.UserRetrofitInterface
import com.example.beginvegan.util.Constants
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService(val userInterface: UserInterface){
    private val userRetrofitInterface: UserRetrofitInterface = ApplicationClass.sRetrofit.create(UserRetrofitInterface::class.java)
    private val accessToken = ("Bearer "+(ApplicationClass.xAccessToken))

    fun tryPostUserVeganType(veganType: String){
        userRetrofitInterface.postUserVeganType(accessToken,veganType).enqueue(object: Callback<VeganTypeResponse>{
            override fun onResponse(
                call: Call<VeganTypeResponse>,
                response: Response<VeganTypeResponse>
            ) {
                if(response.code() == 200){
                    userInterface.onPostUserVeganTypeSuccess(response.body() as VeganTypeResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        userInterface.onPostUserVeganTypeFailure(errorResponse.message)
                    }catch(e:Exception){
                        userInterface.onPostUserVeganTypeFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<VeganTypeResponse>, t: Throwable) {
                userInterface.onPostUserVeganTypeFailure(t.message?:"통신 오류")
            }

        })
    }
}