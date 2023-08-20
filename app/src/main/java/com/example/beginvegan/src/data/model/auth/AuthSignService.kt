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

    fun tryPostAuthSignIn(auth: KakaoAuth){
        authRetrofitInterface.postAuthSignIn(AuthLogin(auth.providerId!!,auth.email)).enqueue(object: Callback<AuthSignResponse>{
            override fun onResponse(call: Call<AuthSignResponse>, response: Response<AuthSignResponse>) {
                if(response.code() == 200){
                    authSignInterface.onPostAuthSignInSuccess(response.body() as AuthSignResponse)
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

            override fun onFailure(call: Call<AuthSignResponse>, t: Throwable) {
                authSignInterface.onPostAuthSignInFailed(t.message?:"통신 오류")
            }

        })
    }
    fun tryPostAuthSignUp(auth: KakaoAuth){
        authRetrofitInterface.postAuthSignUp(auth).enqueue(object: Callback<AuthSignResponse> {
            override fun onResponse(call: Call<AuthSignResponse>, response: Response<AuthSignResponse>) {
                if(response.code() == 200){
                    authSignInterface.onPostAuthSignUpSuccess(response.body() as AuthSignResponse)
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

            override fun onFailure(call: Call<AuthSignResponse>, t: Throwable) {
                authSignInterface.onPostAuthSignUpFailed(t.message?:"통신 오류")
            }

        })
    }

}