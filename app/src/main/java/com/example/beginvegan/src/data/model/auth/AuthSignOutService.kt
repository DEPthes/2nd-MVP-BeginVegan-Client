package com.example.beginvegan.src.data.model.auth

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.AuthRetrofitInterface
import com.example.beginvegan.src.data.model.magazine.MagazineTwoResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class AuthSignOutService(val authSignOutInterface: AuthSignOutInterface) {
    private val authRetrofitInterface: AuthRetrofitInterface =
        ApplicationClass.sRetrofit.create(AuthRetrofitInterface::class.java)

    private fun tryPostAuthSignOut(accessToken: String) {
        authRetrofitInterface.postAuthSignOut(accessToken).enqueue(object :
            Callback<AuthSignOutResponse> {
            override fun onResponse(
                call: Call<AuthSignOutResponse>,
                response: Response<AuthSignOutResponse>
            ) {
                if(response.code() == 200){
                    authSignOutInterface.onPostAuthSignOutSuccess(response.body() as AuthSignOutResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        authSignOutInterface.onPostAuthSignOutFailure(errorResponse.message)
                    }catch(e:Exception){
                        authSignOutInterface.onPostAuthSignOutFailure(e.message?:"통신 오류")
                    }
                }

            }

            override fun onFailure(call: Call<AuthSignOutResponse>, t: Throwable) {
                authSignOutInterface.onPostAuthSignOutFailure(t.message?:"통신 오류")
            }

        })

    }
}