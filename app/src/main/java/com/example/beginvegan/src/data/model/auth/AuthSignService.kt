package com.example.beginvegan.src.data.model.auth

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.AuthRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthSignService(val authSignInterface: AuthSignInterface) {
    private val authRetrofitInterface: AuthRetrofitInterface = ApplicationClass.sRetrofit.create(AuthRetrofitInterface::class.java)

    fun tryPostAuthSignIn(auth: Auth){
        authRetrofitInterface.postAuthSignIn(AuthLogin(auth.providerId,auth.email)).enqueue(object: Callback<AuthLoginResponse>{
            override fun onResponse(call: Call<AuthLoginResponse>, response: Response<AuthLoginResponse>) {
                if(response.code() == 200){
                    authSignInterface.onPostAuthSignInSuccess(response.body() as AuthLoginResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        authSignInterface.onPostAuthSignInFailed(errorResponse.message)
                    }catch(e:Exception){
                        authSignInterface.onPostAuthSignInFailed(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<AuthLoginResponse>, t: Throwable) {
                authSignInterface.onPostAuthSignInFailed(t.message?:"통신 오류")
            }

        })
    }
    fun tryPostAuthSignUp(auth: Auth){
        authRetrofitInterface.postAuthSignUp(auth).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.code() == 200){
                    authSignInterface.onPostAuthSignUpSuccess(response.body() as AuthResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        authSignInterface.onPostAuthSignUpFailed(errorResponse.message)
                    }catch(e:Exception){
                        authSignInterface.onPostAuthSignUpFailed(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                authSignInterface.onPostAuthSignUpFailed(t.message?:"통신 오류")
            }

        })
    }

}