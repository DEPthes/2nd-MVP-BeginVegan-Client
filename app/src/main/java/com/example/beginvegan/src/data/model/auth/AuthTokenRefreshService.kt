package com.example.beginvegan.src.data.model.auth

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.src.data.api.AuthRetrofitInterface

class AuthTokenRefreshService(val authTokenRefreshInterface: AuthTokenRefreshInterface) {
    private val authRetrofitInterface: AuthRetrofitInterface = ApplicationClass.sRetrofit.create(AuthRetrofitInterface::class.java)

}