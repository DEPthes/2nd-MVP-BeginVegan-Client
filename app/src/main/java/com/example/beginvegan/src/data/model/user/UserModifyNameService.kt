package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.UserRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserModifyNameService(val userModifyNameInterface: UserModifyNameInterface) {
    private val userRetrofitInterface: UserRetrofitInterface =
        ApplicationClass.sRetrofit.create(UserRetrofitInterface::class.java)

    fun tryPostUserModifyName() {
        userRetrofitInterface.postUserModifyName(ApplicationClass.xAccessToken).enqueue(object :
            Callback<UserModifyNameResponse> {
            override fun onResponse(
                call: Call<UserModifyNameResponse>,
                response: Response<UserModifyNameResponse>
            ) {
                if(response.code() == 200){
                    userModifyNameInterface.onPostUserModifyNameSuccess(response.body() as UserModifyNameResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        userModifyNameInterface.onPostUserModifyNameFailure(errorResponse.message)
                    }catch(e:Exception){
                        userModifyNameInterface.onPostUserModifyNameFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<UserModifyNameResponse>, t: Throwable) {
                userModifyNameInterface.onPostUserModifyNameFailure(t.message?:"통신 오류")
            }

        })
    }
}