package com.example.beginvegan.src.data.model.auth

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.src.data.api.AuthRetrofitInterface
import retrofit2.create

class AuthSignOutService(val authSignOutInterface: AuthSignOutInterface) {
    private val authRetrofitInterface: AuthRetrofitInterface = ApplicationClass.sRetrofit.create(AuthRetrofitInterface::class.java)

    private fun tryPostAuthSignOut(){
        
    }
}