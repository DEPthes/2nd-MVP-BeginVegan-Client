package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.UserRetrofitInterface
import com.example.beginvegan.util.VeganTypes
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserVeganService(val userInterface: UserInterface){
    private val userRetrofitInterface: UserRetrofitInterface = ApplicationClass.sRetrofit.create(UserRetrofitInterface::class.java)

    fun tryPostUserVeganType(veganType: String){
        userRetrofitInterface.postUserVeganType(ApplicationClass.xAccessToken,VeganType(veganType)).enqueue(object: Callback<UserVeganResponse>{
            override fun onResponse(
                call: Call<UserVeganResponse>,
                response: Response<UserVeganResponse>
            ) {
                if(response.code() == 200){
                    userInterface.onPostUserVeganTypeSuccess(response.body() as UserVeganResponse)
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

            override fun onFailure(call: Call<UserVeganResponse>, t: Throwable) {
                userInterface.onPostUserVeganTypeFailure(t.message?:"통신 오류")
            }

        })
    }
}