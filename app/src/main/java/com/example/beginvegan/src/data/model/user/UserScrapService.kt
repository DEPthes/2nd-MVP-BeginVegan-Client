package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.UserRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserScrapService(val userScrapInterface: UserScrapInterface) {
    private val userRetrofitInterface: UserRetrofitInterface = ApplicationClass.sRetrofit.create(UserRetrofitInterface::class.java)

    fun tryGetUserBookmarks(pageNo:Int){
        userRetrofitInterface.getUserBookmarks(ApplicationClass.xAccessToken,pageNo).enqueue(object:
            Callback<UserScrapResponse> {
            override fun onResponse(
                call: Call<UserScrapResponse>,
                response: Response<UserScrapResponse>
            ) {
                if(response.code() == 200){
                    if(pageNo == 0){
                        userScrapInterface.onGetUserBookmarksSuccess(response.body() as UserScrapResponse)
                    }else{
                        userScrapInterface.onGetUserBookmarksAddSuccess(response.body() as UserScrapResponse)
                    }
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        userScrapInterface.onGetUserBookmarksFailure(errorResponse.message)
                    }catch(e:Exception){
                        userScrapInterface.onGetUserBookmarksFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<UserScrapResponse>, t: Throwable) {
                userScrapInterface.onGetUserBookmarksFailure(t.message?:"통신 오류")
            }

        })
    }
}