package com.example.beginvegan.src.data.model.auth

interface AuthSignUpInterface {
    fun onPostAuthSignUpSuccess(response: AuthResponse)
    fun onPostAuthSignUpFailed(message: String)
}