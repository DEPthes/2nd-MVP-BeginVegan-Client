package com.example.beginvegan.src.data.model.auth

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.AuthRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthSignUpService(val authSignUpInterface: AuthSignUpInterface) {
    private val authRetrofitInterface: AuthRetrofitInterface = ApplicationClass.sRetrofit.create(AuthRetrofitInterface::class.java)

    fun tryPostAuthSignUp(auth: Auth){
        authRetrofitInterface.postAuthSignUp(auth).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.code() == 200){
                    authSignUpInterface.onPostAuthSignUpSuccess(response.body() as AuthResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        authSignUpInterface.onPostAuthSignUpFailed(errorResponse.message)
                    }catch(e:Exception){
                        authSignUpInterface.onPostAuthSignUpFailed(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                authSignUpInterface.onPostAuthSignUpFailed(t.message?:"통신 오류")
            }

        })
    }

}