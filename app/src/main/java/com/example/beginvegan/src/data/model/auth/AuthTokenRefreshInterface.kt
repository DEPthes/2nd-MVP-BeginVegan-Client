package com.example.beginvegan.src.data.model.auth

interface AuthTokenRefreshInterface {
    fun onPostTokenRefreshSuccess(response: AuthTokenResponse)
    fun onPostTokenRefreshFailure(message: String)
}