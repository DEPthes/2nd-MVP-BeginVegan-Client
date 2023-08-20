package com.example.beginvegan.src.data.model.auth

interface AuthSignInterface {
    fun onPostAuthSignInSuccess(response: AuthLoginResponse)
    fun onPostAuthSignInFailed(message: String)
    fun onPostAuthSignUpSuccess(response: AuthResponse)
    fun onPostAuthSignUpFailed(message: String)
}