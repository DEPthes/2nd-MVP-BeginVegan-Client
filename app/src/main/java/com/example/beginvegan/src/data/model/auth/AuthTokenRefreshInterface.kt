package com.example.beginvegan.src.data.model.auth

interface AuthTokenRefreshInterface {
    fun onPostTokenRefreshSuccess(response: AuthResponse)
    fun onPostTokenRefreshFailure(message: String)
}